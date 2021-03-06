package com.forttiori.multimodulereactivewebflux.truck.model;


import com.forttiori.multimodulereactivewebflux.repository.entity.TruckEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor

public class TruckServiceMapper {

      public static TruckEntity truckResponseToEntity(TruckServiceResponse truckServiceResponse){
        return TruckEntity.builder()
                .id(truckServiceResponse.getId())
                .name(truckServiceResponse.getName())
                .routesEntity(truckServiceResponse.getRoutesEntity())
                .build();
    }

    public static TruckServiceResponse truckEntityToResponse(TruckEntity truckEntity){
        return TruckServiceResponse.builder()
                .id(truckEntity.getId())
                .name(truckEntity.getName())
                .routesEntity(truckEntity.getRoutesEntity())
                .build();
    }

    public static TruckEntity truckRequestoToEntity(TruckServiceRequest truckServiceRequest){
        return TruckEntity.builder()
                .id(truckServiceRequest.getId())
                .name(truckServiceRequest.getName())
                .routesEntity(truckServiceRequest.getRoutesEntity())
                .build();
    }


}
