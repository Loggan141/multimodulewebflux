package com.v1.truck;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import routes.RoutesServiceRequest;
import truck.model.TruckServiceRequest;
import com.v1.truck.model.TruckControllerResponse;


import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Api(value = "Truck Api Fluxed")
@RequestMapping("/v1/truck")
public class TruckController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TruckController.class);
    private final TruckControllerFacade truckControllerFacade;

    @ApiOperation(value = "Get Trucks")
    @ApiResponse(responseCode = "404",description = "Truck not found")
    @ApiResponse(responseCode = "200",description = "Ok")
    @GetMapping(produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public Flux<TruckControllerResponse> getTrucks(@RequestParam(required = false) List<String> id){
        LOGGER.info("***Getting trucks action called...");
        return this.truckControllerFacade.getAllTrucksOrById(id);
    }


    @ApiOperation(value = "Creates a new Truck")
    @ApiResponses(@ApiResponse(responseCode = "201",description = "Truck Created"))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces="application/json", consumes = "application/json")
    public Mono<TruckControllerResponse> createNewTruck(@RequestBody @Valid TruckServiceRequest truckServiceRequest){
        LOGGER.info("***Creating trucks action called...");
       return this.truckControllerFacade.createsATruck(truckServiceRequest);
    }



    @ApiOperation(value = "Updates an existing Truck")
    @ApiResponses(@ApiResponse(responseCode = "404",description = "Truck not found"))
    @ApiResponse(responseCode = "200",description = "Ok")
    @PutMapping(produces="application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<TruckControllerResponse> updateTruckData(@Valid @RequestBody TruckServiceRequest truckServiceRequest){
              LOGGER.info("***Updating trucks...");
       return this.truckControllerFacade.updatesATruck(truckServiceRequest);
    }



    @ApiOperation(value = "Delete a truck by ID or All")
    @ApiResponses(@ApiResponse(responseCode = "204",description = "No C ontent"))
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTruck(@RequestParam(value = "id", required = false) List<String> id){
        LOGGER.info("***Deleting trucks action called...");
        return this.truckControllerFacade.deletesTruck(id);
    }



    @ApiOperation(value = "Updates a Truck's Route for an existing Truck")
    @ApiResponse(responseCode = "404",description = "Truck not found")
    @ApiResponse(responseCode = "201",description = "OK")
    @PutMapping(path = "/{id}/route", produces="application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TruckControllerResponse> updateTruckRouteById(@PathVariable String id,
                                                           @RequestBody @Valid RoutesServiceRequest routesServiceRequest){
        LOGGER.info("***Updating truck's route action called...");
        return this.truckControllerFacade.updatesRoute(id, routesServiceRequest); }



    @ApiOperation(value = "Deletes a Truck's Route")
    @ApiResponse(responseCode = "404",description = "Truck not found")
    @ApiResponse(responseCode = "204",description = "Deleted Route")
    @DeleteMapping("/{id}/route")
    public Mono<TruckControllerResponse> deleteRouteTruckById(@PathVariable String id){
        LOGGER.info("***Deleting truck's route action called...");
     return this.truckControllerFacade.deletesRoute(id);
    }

}