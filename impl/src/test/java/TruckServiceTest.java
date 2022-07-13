
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import com.forttiori.multimodulereactivewebflux.repository.TruckRepository;
import com.forttiori.multimodulereactivewebflux.routes.RoutesIntegration;
import stub.RouteStub;
import stub.TruckStub;
import com.forttiori.multimodulereactivewebflux.truck.TruckService;
import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@AutoConfigureDataMongo
@EnableMongoRepositories("com.forttiori.reactivewebflux.v1.repository")
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.4.6")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TruckService.class, TruckRepository.class, RoutesIntegration.class})
class TruckServiceTest {
    @InjectMocks
    TruckService truckService;
    @Mock
    TruckRepository truckRepositoryMock;
    @Mock
    RoutesIntegration routeIntegration;


    @Test
    void whenSearchTruckByIdReturnsListOfTrucks(){

        Mockito.when(this.truckRepositoryMock.findAllById(List.of("12321F")))
               .thenReturn(Flux.just(TruckStub.expectedEntityTruck()));
        //testar cenario de erro com stepverifier
        StepVerifier.create(this.truckService.findTruckById(List.of("12321F")))
                    .assertNext(truckResponse -> assertEquals(TruckStub.expectedTruckToReturn(), truckResponse))
                    .verifyComplete();
     }

    @Test
    void whenSearchsAllTrucksReturnsListOfTrucks(){

        Mockito.when(this.truckRepositoryMock.findAll())
               .thenReturn(Flux.just(TruckStub.expectedEntityTruck()));

        StepVerifier.create(this.truckService.findAllTrucks())
                    .assertNext(truckResponse -> assertEquals(TruckStub.expectedTruckToReturn(), truckResponse))
                    .verifyComplete();
     }

    @Test
    void createsNewTruckAndShouldReturnTruck(){

        Mockito.when(truckRepositoryMock.save(TruckStub.createdTruck()))
               .thenReturn(Mono.just(TruckStub.expectedEntityTruck()));

        StepVerifier.create(this.truckService.createTruck(TruckStub.truckToCreate()))
                .assertNext(truckResponse -> assertEquals(TruckStub.expectedTruckToReturn(), truckResponse))
                .verifyComplete();

    }

    @Test
    void updatesTruckAndShouldReturnTruck(){

        Mockito.when(truckRepositoryMock.findById("12321F"))
               .thenReturn(Mono.just(TruckServiceMapper.truckResponseToEntity(TruckStub.expectedTruckToReturn())));

        Mockito.when(truckRepositoryMock.save(TruckStub.expectedEntityTruck()))
               .thenReturn(Mono.just(TruckServiceMapper.truckResponseToEntity(TruckStub.expectedUpdatedTruckToReturn())));

        StepVerifier.create(this.truckService.updateDataByIdOrThrowsTruckNotFoundException(TruckStub.truckToUpdate()))
                .assertNext(truckResponse -> assertEquals(TruckStub.expectedUpdatedTruckToReturn(), truckResponse))
                .verifyComplete();
    }

    @Test
    void updateARoutesTruck_ReturnsATruck(){

        Mockito.when(truckRepositoryMock.findById("12321F"))
               .thenReturn(Mono.just(TruckServiceMapper.truckResponseToEntity(TruckStub.expectedTruckToReturn())));

        Mockito.when(routeIntegration.getRoutes(RouteStub.routesIntegrationRequest()))
               .thenReturn(Mono.just(RouteStub.expectedRouteIntegrationResponse()));

        Mockito.when(truckRepositoryMock.save(any()))
                .thenReturn(Mono.just(RouteStub.truckToSaveWithNewRoute()));


        StepVerifier.create(this.truckService.updateTrucksRouteOrThrowsTruckNotFoundException("12321F",RouteStub.routesRequest()))
                .assertNext(truckResponse -> assertEquals(RouteStub.truckWithNewRouteResponse(), truckResponse))
                .verifyComplete();
    }

    @Test
    void deletesATrucksRoute(){

        Mockito.when(truckRepositoryMock.findById("12321F")).thenReturn(Mono.just(RouteStub.truckToSaveWithNewRoute()));

        Mockito.when(truckRepositoryMock.save(any())).thenReturn(Mono.just(RouteStub.truckToSaveWithDeletedRouteEntity()));

        StepVerifier.create(this.truckService.deleteRouteTruckByIdOrThrowsTruckNotFoundException("12321F"))
                .assertNext(truckResponse -> assertEquals(RouteStub.truckToSaveWithDeletedRoute(), truckResponse))
                .verifyComplete();
    }

    @Test
    void deletesATruckById(){

        Mockito.when(truckRepositoryMock.deleteAllById(List.of("12321F"))).thenReturn(Mono.empty());

        StepVerifier.create(this.truckService.deleteTrucksById(List.of("12321F")))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void deletesAllTruck(){
        Mockito.when(truckRepositoryMock.deleteAll()).thenReturn(Mono.empty());

        StepVerifier.create(this.truckService.deleteAllTrucks())
                .expectSubscription()
                .verifyComplete();
    }



}

