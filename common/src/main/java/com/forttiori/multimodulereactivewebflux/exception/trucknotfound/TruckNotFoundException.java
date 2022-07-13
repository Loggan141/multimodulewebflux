package com.forttiori.multimodulereactivewebflux.exception.trucknotfound;

public class TruckNotFoundException extends RuntimeException{

    private final String message;

    public TruckNotFoundException (String message){
        super(message);
        this.message=message;
    }
}
