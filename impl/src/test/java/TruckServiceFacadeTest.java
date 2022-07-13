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
import truck.TruckService;
import truck.TruckServiceFacade;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TruckServiceFacade.class, TruckService.class})
class TruckServiceFacadeTest {
    @InjectMocks
    TruckServiceFacade truckServiceFacade;
    @Mock
    TruckService truckServiceMock;

    @Test
    void findTrucksById() {
        var expected = TruckStub.expectedTruckToReturn();

        Mockito.when(truckServiceMock.findTruckById(any())).thenReturn(Flux.just(TruckStub.expectedTruckToReturn()));

        StepVerifier.create(this.truckServiceFacade.findTrucksByIdOrFindsAll(List.of("12321F")))
                .assertNext(truckResponse -> assertEquals(expected, truckResponse))
                .verifyComplete();
    }
    @Test
    void findAllTrucks() {
        var expected = TruckStub.expectedTruckToReturn();

        Mockito.when(truckServiceMock.findAllTrucks()).thenReturn(Flux.just(TruckStub.expectedTruckToReturn()));

        StepVerifier.create(this.truckServiceFacade.findTrucksByIdOrFindsAll(List.of()))
                .assertNext(truckResponse -> assertEquals(expected, truckResponse))
                .verifyComplete();
    }

    @Test
    void createsTruck() {
        var expected = TruckStub.expectedTruckToReturn();

        Mockito.when(truckServiceMock.createTruck(any())).thenReturn(Mono.just(TruckStub.expectedTruckToReturn()));

        StepVerifier.create(truckServiceFacade.createsTruck(TruckStub.truckToCreate()))
                    .assertNext(truckResponse -> assertEquals(expected, truckResponse))
                    .verifyComplete();
    }

    @Test
    void updatesATruck() {
        var expected = TruckStub.expectedTruckToReturn();

        Mockito.when(truckServiceMock.updateDataByIdOrThrowsTruckNotFoundException(TruckStub.truckToUpdate()))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(truckServiceFacade.updatesATruck(TruckStub.truckToUpdate()))
                .assertNext(truckResponse -> assertEquals(expected, truckResponse))
                .verifyComplete();
    }

    @Test
    void updatesARouteFromATruck() {
        var expected = RouteStub.truckWithNewRouteResponse();

        Mockito.when(truckServiceMock.updateTrucksRouteOrThrowsTruckNotFoundException("12321F", RouteStub.routesRequest()))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(truckServiceFacade.updatesARouteFromATruck("12321F", RouteStub.routesRequest()))
                .assertNext(truckResponse -> assertEquals(expected, truckResponse))
                .verifyComplete();
    }

    @Test
    void deletesARouteFromATruck() {
        var expected = RouteStub.truckToSaveWithDeletedRoute();

        Mockito.when(truckServiceMock.deleteRouteTruckByIdOrThrowsTruckNotFoundException("12321F"))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(truckServiceFacade.deletesARouteFromATruck("12321F"))
                .assertNext(truckResponse -> assertEquals(expected, truckResponse))
                .verifyComplete();
    }

    @Test
    void deletesATruckById(){

        Mockito.when(truckServiceMock.deleteTrucksById(any())).thenReturn(Mono.empty());

        StepVerifier.create(truckServiceFacade.deletesTruckByIdOrDeletesAll(List.of("12321F")))
                .expectSubscription().verifyComplete();
    }
    @Test
    void deletesAllTrucks(){

        Mockito.when(truckServiceMock.deleteAllTrucks()).thenReturn(Mono.empty());

        StepVerifier.create(truckServiceFacade.deletesTruckByIdOrDeletesAll(List.of()))
                .expectSubscription().verifyComplete();
    }


}