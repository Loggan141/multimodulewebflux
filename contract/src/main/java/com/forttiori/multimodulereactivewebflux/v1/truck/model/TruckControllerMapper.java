package com.forttiori.multimodulereactivewebflux.v1.truck.model;


import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceResponse;

public class TruckControllerMapper {

    public static TruckControllerResponse serviceResponseToControllerResponse(TruckServiceResponse truckServiceResponseResponse){
        return TruckControllerResponse.builder()
                .id(truckServiceResponseResponse.getId())
                .name(truckServiceResponseResponse.getName())
                .routesEntity(truckServiceResponseResponse.getRoutesEntity())
                .build();
    }

}
