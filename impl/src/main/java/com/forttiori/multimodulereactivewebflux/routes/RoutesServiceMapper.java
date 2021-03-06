package com.forttiori.multimodulereactivewebflux.routes;

import com.forttiori.multimodulereactivewebflux.repository.entity.RoutesEntity;
import com.forttiori.multimodulereactivewebflux.routes.model.request.RoutesIntegrationRequest;
import com.forttiori.multimodulereactivewebflux.routes.model.response.RoutesIntegrationResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class RoutesServiceMapper {

  public static RoutesEntity IntegrationResponseToRouteResponse(RoutesIntegrationResponse routesIntegrationResponse){

            var distanceOptional = routesIntegrationResponse.getRows().stream()
                    .flatMap(valueRowsIn->valueRowsIn.getElements().stream()
                            .map(value->value.getDistance().getText()))
                    .findFirst();

            String distance = distanceOptional.orElseThrow(()->new RuntimeException("Distance is empty!"));

            var durationOptional = routesIntegrationResponse.getRows().stream()
                    .flatMap(valueRowsIn->valueRowsIn.getElements().stream()
                            .map(element->element.getDuration().getText()))
                    .findFirst();

            String duration = durationOptional.orElseThrow(()->new RuntimeException("Duration is empty!"));

            return RoutesEntity.builder()
                    .destinationAddress(routesIntegrationResponse.getDestinationAddresses().get(0))
                    .originAddress(routesIntegrationResponse.getOriginAddresses().get(0))
                    .duration(duration)
                    .distance(distance)
                    .build();
        }

        public static RoutesIntegrationRequest integrationRequestToRoutesRequest(RoutesServiceRequest routesIntegrationRequest){
            return RoutesIntegrationRequest.builder()
                    .originAddress(routesIntegrationRequest.getOriginAddress())
                    .destinationAddress(routesIntegrationRequest.getDestinationAddress())
                    .build();

        }



}
