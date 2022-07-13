package com.v1.truck.model;


import truck.model.TruckServiceResponse;

public class TruckControllerMapper {

    public static TruckControllerResponse serviceResponseToControllerResponse(TruckServiceResponse truckServiceResponseResponse){
        return TruckControllerResponse.builder()
                .id(truckServiceResponseResponse.getId())
                .name(truckServiceResponseResponse.getName())
                .routesEntity(truckServiceResponseResponse.getRoutesEntity())
                .build();
    }

}
