package com.forttiori.multimodulereactivewebflux.truck;


import com.forttiori.multimodulereactivewebflux.exception.trucknotfound.TruckNotFoundMonoResponse;
import com.forttiori.multimodulereactivewebflux.repository.TruckRepository;
import com.forttiori.multimodulereactivewebflux.repository.entity.TruckEntity;
import com.forttiori.multimodulereactivewebflux.routes.RoutesServiceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.forttiori.multimodulereactivewebflux.routes.RoutesIntegration;
import com.forttiori.multimodulereactivewebflux.routes.RoutesServiceRequest;
import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceMapper;
import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceRequest;
import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceResponse;

import java.util.List;
import java.util.function.Function;


@AllArgsConstructor
@Service
public class TruckService{
    private TruckRepository truckRepository;
    private RoutesIntegration routesIntegration;

    public Flux<TruckServiceResponse> findTruckById(List<String> ids){//Flux Error
        return this.truckRepository.findAllById(ids)           //simular exemplo de erro switchIfEmpty
                                   .switchIfEmpty(new TruckNotFoundMonoResponse().monoResponseTruckNotFoundException())
                                   .map(TruckServiceMapper::truckEntityToResponse);
    }
     public Flux<TruckServiceResponse> findAllTrucks(){
       return this.truckRepository.findAll()
                                  .map(TruckServiceMapper::truckEntityToResponse);
    }

    public Mono<TruckServiceResponse> createTruck(TruckServiceRequest truckServiceRequest) {
       return this.truckRepository.save(TruckServiceMapper.truckRequestoToEntity(truckServiceRequest))
                                   .map(TruckServiceMapper::truckEntityToResponse);
    }

    public Mono<Void> deleteTrucksById(List<String> ids) {
        return this.truckRepository.deleteAllById(ids);
    }

    public Mono<Void> deleteAllTrucks() {
         return this.truckRepository.deleteAll();
    }

    public Mono<TruckServiceResponse> updateDataByIdOrThrowsTruckNotFoundException(TruckServiceRequest truckServiceRequest) {
        Mono<TruckEntity> truckMonoToSave = this.truckRepository.findById(truckServiceRequest.getId())
                                                                .switchIfEmpty(new TruckNotFoundMonoResponse().monoResponseTruckNotFoundException());

        return truckMonoToSave.flatMap(existingTruckToSave->{
                                       existingTruckToSave.setId(truckServiceRequest.getId());
                                       existingTruckToSave.setName(truckServiceRequest.getName());
                                       return truckRepository.save(existingTruckToSave);})
                .map(TruckServiceMapper::truckEntityToResponse);
    }

    public Mono<TruckServiceResponse> updateTrucksRouteOrThrowsTruckNotFoundException(String id,
                                                                                      RoutesServiceRequest routesServiceRequest){
        Mono<TruckEntity> truckToSave = this.truckRepository.findById(id)
                                                            .switchIfEmpty(new TruckNotFoundMonoResponse().monoResponseTruckNotFoundException());
        return truckToSave.flatMap(mapGetRouteAndSave(routesServiceRequest)).flatMap(mapToGetTruckAndSaveWithNewRoute(id));
    }



    //Mappers criados para reduzir o tamanho do fluxo no "Update Trucks Route Or Throws Truck Not Found"
    private Function<TruckEntity, Mono<TruckServiceResponse>> mapGetRouteAndSave(RoutesServiceRequest routesServiceRequest) {
        return existingTruck -> routesIntegration.getRoutes(RoutesServiceMapper.integrationRequestToRoutesRequest(routesServiceRequest))
                .map(RoutesServiceMapper::IntegrationResponseToRouteResponse)
                .map(routeEntity -> {
                                        existingTruck.setRoutesEntity(routeEntity);
                                        return existingTruck;
                }).map(TruckServiceMapper::truckEntityToResponse);
    }
    //Mappers criados para reduzir o tamanho do fluxo no "Update Trucks Route Or Throws Truck Not Found"
    private Function<TruckServiceResponse,Mono<TruckServiceResponse>> mapToGetTruckAndSaveWithNewRoute(String id){
           return existingTruck -> {
                                    existingTruck.setId(id);
                                    existingTruck.setName(existingTruck.getName());
                                    existingTruck.getRoutesEntity().setFreightCost(freightCost(existingTruck.getRoutesEntity().getDistance()));
                                    TruckServiceMapper.truckResponseToEntity(existingTruck);
           return this.truckRepository.save(TruckServiceMapper.truckResponseToEntity(existingTruck)).map(TruckServiceMapper::truckEntityToResponse);
        };
    }

    public Mono<TruckServiceResponse> deleteRouteTruckByIdOrThrowsTruckNotFoundException(String id){
        var truckMonoToSave = this.truckRepository.findById(id)
                     .switchIfEmpty(new TruckNotFoundMonoResponse().monoResponseTruckNotFoundException());

       return truckMonoToSave.flatMap(truckEntity -> {
           truckEntity.setId(truckEntity.getId());
           truckEntity.setName(truckEntity.getName());
           truckEntity.setRoutesEntity(null);

           return this.truckRepository.save(truckEntity).map(TruckServiceMapper::truckEntityToResponse);
       });
    }

    //Calculo do frete
    public Double freightCost(String distance){
        double distanceToFreight = Double.parseDouble(distance.replaceAll("\\D", ""));
        double distanceToFreightCost=0.0;
        if(distanceToFreight>=1 && distanceToFreight<=100){distanceToFreightCost=(distanceToFreight*2.19);}
        else if((distanceToFreight>100 && distanceToFreight<=200)){distanceToFreightCost=(distanceToFreight*1.35);}
        else if((distanceToFreight>200 && distanceToFreight<=300)){distanceToFreightCost=(distanceToFreight*1.18);}
        else if((distanceToFreight>300 && distanceToFreight<=400)){distanceToFreightCost=(distanceToFreight*1.11);}
        else if((distanceToFreight>400 && distanceToFreight<=500)){distanceToFreightCost=(distanceToFreight*1.07);}
        else if(distanceToFreight>500){distanceToFreightCost=(distanceToFreight*1.01);}

        return distanceToFreightCost;
    }

}


