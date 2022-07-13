package com.forttiori.multimodulereactivewebflux.exception.trucknotfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TruckNotFoundMonoResponse extends RuntimeException {
    public <T> Mono<T> monoResponseTruckNotFoundException(){
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Truck Not Found"));
    }
}
