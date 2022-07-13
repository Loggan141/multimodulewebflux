import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import stub.RouteStub;
import stub.TruckStub;
import stub.TruckStubMapper;
import com.forttiori.multimodulereactivewebflux.truck.TruckServiceFacade;
import com.forttiori.multimodulereactivewebflux.v1.truck.TruckControllerFacade;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TruckControllerFacade.class, TruckServiceFacade.class})
class TruckControllerFacadeTest {

    @InjectMocks
    TruckControllerFacade truckControllerFacade;
    @Mock
    TruckServiceFacade truckServiceFacadeMock;

    @Test
    void getAllTrucksOrById() {

    var expected = TruckStub.expectedTruckToReturn();
    var expectedController= TruckStubMapper.serviceResposeToControllerResponse(expected);

        Mockito.when(truckServiceFacadeMock.findTrucksByIdOrFindsAll(List.of("12321F")))
                .thenReturn(Flux.just(expected));

        StepVerifier.create(truckControllerFacade.getAllTrucksOrById(List.of("12321F")))
                .assertNext(truckControllerResponse -> assertEquals(expectedController, truckControllerResponse))
                .verifyComplete();
    }

    @Test
    void createsATruck() {
        var expected = TruckStub.expectedTruckToReturn();
        var expectedController= TruckStubMapper.serviceResposeToControllerResponse(expected);

        Mockito.when(truckServiceFacadeMock.createsTruck(any())).thenReturn(Mono.just(TruckStub.expectedTruckToReturn()));

        StepVerifier.create(truckControllerFacade.createsATruck(TruckStub.truckToCreate()))
                .assertNext(truckResponse -> assertEquals(expectedController, truckResponse))
                .verifyComplete();
    }

    @Test
    void updatesATruck() {
        var expected = TruckStub.expectedTruckToReturn();
        var expectedController= TruckStubMapper.serviceResposeToControllerResponse(expected);

        Mockito.when(truckServiceFacadeMock.updatesATruck(TruckStub.truckToUpdate()))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(truckControllerFacade.updatesATruck(TruckStub.truckToUpdate()))
                .assertNext(truckResponse -> assertEquals(expectedController, truckResponse))
                .verifyComplete();
    }

    @Test
    void deletesTruckById() {
        Mockito.when(truckServiceFacadeMock.deletesTruckByIdOrDeletesAll(any())).thenReturn(Mono.empty());

        StepVerifier.create(truckControllerFacade.deletesTruck(List.of("12321F")))
                .expectSubscription().verifyComplete();
    }
    @Test
    void deletesAllTrucks() {
        Mockito.when(truckServiceFacadeMock.deletesTruckByIdOrDeletesAll(any())).thenReturn(Mono.empty());

        StepVerifier.create(truckControllerFacade.deletesTruck(List.of()))
                .expectSubscription().verifyComplete();
    }



    @Test
    void updatesRoute() {
        var expected = RouteStub.truckWithNewRouteResponse();
        var expectedController= TruckStubMapper.serviceResposeToControllerResponse(expected);

        Mockito.when(truckServiceFacadeMock.updatesARouteFromATruck("12321F", RouteStub.routesRequest()))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(truckControllerFacade.updatesRoute("12321F", RouteStub.routesRequest()))
                .assertNext(truckResponse -> assertEquals(expectedController, truckResponse))
                .verifyComplete();
    }

    @Test
    void deletesRoute() {
        var expected = RouteStub.truckToSaveWithDeletedRoute();
        var expectedController= TruckStubMapper.serviceResposeToControllerResponse(expected);


        Mockito.when(truckServiceFacadeMock.deletesARouteFromATruck("12321F"))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(truckControllerFacade.deletesRoute("12321F"))
                .assertNext(truckResponse -> assertEquals(expectedController, truckResponse))
                .verifyComplete();
    }
}