package com.forttiori.multimodulereactivewebflux.exception.routeerror;

import lombok.Data;

@Data
public class RouteErrorException extends RuntimeException {

    private final RouteErrorResponse routeErrorResponse;

    public RouteErrorException(RouteErrorResponse routeErrorResponse) {
        super();
        this.routeErrorResponse = routeErrorResponse;
    }

}
