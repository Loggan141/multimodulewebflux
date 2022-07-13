package com.forttiori.multimodulereactivewebflux.v1.truck;


import com.forttiori.multimodulereactivewebflux.truck.TruckServiceFacade;
import com.forttiori.multimodulereactivewebflux.v1.truck.model.TruckControllerMapper;
import com.forttiori.multimodulereactivewebflux.v1.truck.model.TruckControllerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.forttiori.multimodulereactivewebflux.routes.RoutesServiceRequest;
import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceRequest;

import java.util.List;

@Component
@AllArgsConstructor
public class TruckControllerFacade {
    private final TruckServiceFacade truckServiceFacade;

    public Flux<TruckControllerResponse> getAllTrucksOrById(List<String> ids){
                return truckServiceFacade.findTrucksByIdOrFindsAll(ids)
                        .map(TruckControllerMapper::serviceResponseToControllerResponse);
    }

    public Mono<TruckControllerResponse> createsATruck(TruckServiceRequest truckServiceRequest){
        return truckServiceFacade.createsTruck(truckServiceRequest)
                                 .map(TruckControllerMapper::serviceResponseToControllerResponse);

    }
    public Mono<TruckControllerResponse> updatesATruck(TruckServiceRequest truckServiceRequest){
        return truckServiceFacade.updatesATruck(truckServiceRequest)
                                 .map(TruckControllerMapper::serviceResponseToControllerResponse);
    }

    public Mono<Void> deletesTruck(List<String> ids) {
        return truckServiceFacade.deletesTruckByIdOrDeletesAll(ids);
    }

    public Mono<TruckControllerResponse> updatesRoute(String id, RoutesServiceRequest routesServiceRequest){

        return truckServiceFacade.updatesARouteFromATruck(id, routesServiceRequest)
                                 .map(TruckControllerMapper::serviceResponseToControllerResponse);
    }
     public Mono<TruckControllerResponse> deletesRoute(String id){
        return truckServiceFacade.deletesARouteFromATruck(id)
                                 .map(TruckControllerMapper::serviceResponseToControllerResponse);
    }

}
