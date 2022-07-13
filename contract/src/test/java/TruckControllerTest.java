import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.forttiori.multimodulereactivewebflux.repository.TruckRepository;
import stub.RouteStub;
import stub.TruckStub;
import stub.TruckStubMapper;
import com.forttiori.multimodulereactivewebflux.truck.TruckService;
import com.forttiori.multimodulereactivewebflux.truck.TruckServiceFacade;
import com.forttiori.multimodulereactivewebflux.v1.truck.TruckController;
import com.forttiori.multimodulereactivewebflux.v1.truck.TruckControllerFacade;
import com.forttiori.multimodulereactivewebflux.v1.truck.model.TruckControllerResponse;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
@WebFluxTest
@AutoConfigureWebTestClient
@ContextConfiguration(classes = {TruckController.class, TruckService.class, TruckRepository.class,
                                 TruckControllerFacade.class, TruckServiceFacade.class})
class TruckControllerTest {
    @Autowired
    WebTestClient webTestClient;
    @MockBean
    TruckService truckService;

    @Test
    void getTrucksById() {
        var expected = TruckStub.expectedTruckToReturn();
        var controllerExpected = TruckStubMapper.serviceResposeToControllerResponse(expected);

        when(truckService.findTruckById(List.of("12321F")))
                                  .thenReturn(Flux.just(expected));

        webTestClient.get()
                .uri("/v1/truck?id=12321F")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(TruckControllerResponse.class)
                .isEqualTo(List.of(controllerExpected));
    }

    @Test
    void getAllTrucks() {
        var expected = TruckStub.expectedTruckToReturn();
        var controllerExpected = TruckStubMapper.serviceResposeToControllerResponse(expected);

        when(truckService.findAllTrucks())
                                  .thenReturn(Flux.just(expected));

        webTestClient.get()
                .uri("/v1/truck?id=")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(TruckControllerResponse.class)
                .isEqualTo(List.of(controllerExpected));
    }

    @Test
    void createNewTruck() {

        var expectedTruck = TruckStub.expectedTruckToReturn();
        var expectedControllerTruck = TruckStubMapper.serviceResposeToControllerResponse(expectedTruck);

        when(truckService.createTruck(TruckStub.truckToCreate()))
                                  .thenReturn(Mono.just(expectedTruck));

        webTestClient.post()
                     .uri("/v1/truck")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(TruckStub.truckToCreate()))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(TruckControllerResponse.class)
                .isEqualTo(expectedControllerTruck);
    }

    @Test
    void updateTruckData() {

        var expectedTruck = TruckStub.expectedUpdatedTruckToReturn();
        var expectedControllerTruck = TruckStubMapper.serviceResposeToControllerResponse(expectedTruck);

        when(truckService.updateDataByIdOrThrowsTruckNotFoundException(TruckStub.truckToUpdate()))
                .thenReturn(Mono.just(expectedTruck));

        webTestClient.put()
                .uri("/v1/truck")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(TruckStub.truckToUpdate()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(TruckControllerResponse.class)
                .isEqualTo(expectedControllerTruck);
    }

    @Test
    void deleteTruckById() {
        when(truckService.deleteTrucksById(List.of("12321F"))).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/v1/truck?id=12321F")
                .exchange()
                .expectStatus()
                .isNoContent();
    }

     @Test
    void deletesAllTrucks() {
        when(truckService.deleteAllTrucks()).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/v1/truck?id=")
                .exchange()
                .expectStatus()
                .isNoContent();
    }

    @Test
    void updateTruckRouteById() {

        var expectedTruck = RouteStub.truckWithNewRouteResponse();
        var expectedControllerTruck = TruckStubMapper.serviceResposeToControllerResponse(expectedTruck);

        when(truckService.updateTrucksRouteOrThrowsTruckNotFoundException("12321F",RouteStub.routesRequest()))
                    .thenReturn(Mono.just(expectedTruck));


        webTestClient.put()
                .uri("/v1/truck/12321F/route")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(RouteStub.routesRequest()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(TruckControllerResponse.class)
                .isEqualTo(expectedControllerTruck);
    }

//    @Test
//    void updateTruckRouteById_ReturnsNotFound() {
//
////        var expectedTruck = RouteStub.truckWithNewRouteResponse();
////        var expectedControllerTruck = TruckStubMapper.serviceResposeToControllerResponse(expectedTruck);
//
//        when(truckService.updateTrucksRouteOrThrowsTruckNotFoundException(any(),any()))
//                    .thenReturn(Mono.empty());
//
//
//        webTestClient.put()
//                .uri("/v1/truck/123F/route")
//                .contentType(MediaType.APPLICATION_JSON)
//                //.body(BodyInserters.fromValue(RouteStub.routesRequest()))
//                .exchange()
//                .expectStatus()
//                .isNotFound()
//                .expectBody(TruckNotFoundMonoResponse.class)
//                ;
//    }

    @Test
    void deleteRouteTruckById() {
        when(truckService.deleteRouteTruckByIdOrThrowsTruckNotFoundException("12321F")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/v1/truck/12321F/route")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}