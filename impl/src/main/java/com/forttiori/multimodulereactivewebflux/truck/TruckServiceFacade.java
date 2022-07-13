package com.forttiori.multimodulereactivewebflux.truck;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.forttiori.multimodulereactivewebflux.routes.RoutesServiceRequest;
import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceRequest;
import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceResponse;

import java.util.List;

@Component
@AllArgsConstructor
public class TruckServiceFacade {

    private TruckService truckService;

    public Flux<TruckServiceResponse> findTrucksByIdOrFindsAll(List<String> ids){
        if(ids.isEmpty()){
            return truckService.findAllTrucks();
        }
        return truckService.findTruckById(ids);
    }

    public Mono<TruckServiceResponse> createsTruck(TruckServiceRequest truckServiceRequest){
        return truckService.createTruck(truckServiceRequest);

    }

    public Mono<TruckServiceResponse> updatesATruck(TruckServiceRequest truckServiceRequest){
        return truckService.updateDataByIdOrThrowsTruckNotFoundException(truckServiceRequest);
    }

    public Mono<Void> deletesTruckByIdOrDeletesAll(List<String> ids) {
        if (ids.isEmpty()) {
            return truckService.deleteAllTrucks();
        }return truckService.deleteTrucksById(ids);
    }

    public Mono<TruckServiceResponse> updatesARouteFromATruck(String id, RoutesServiceRequest routesServiceRequest){

        return truckService.updateTrucksRouteOrThrowsTruckNotFoundException(id, routesServiceRequest);
    }

    public Mono<TruckServiceResponse> deletesARouteFromATruck(String id){

        return truckService.deleteRouteTruckByIdOrThrowsTruckNotFoundException(id);
    }


}
