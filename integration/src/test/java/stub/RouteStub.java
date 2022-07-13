package stub;

import com.forttiori.multimodulereactivewebflux.routes.model.Distance;
import com.forttiori.multimodulereactivewebflux.routes.model.Duration;
import com.forttiori.multimodulereactivewebflux.routes.model.Element;
import com.forttiori.multimodulereactivewebflux.routes.model.Row;
import com.forttiori.multimodulereactivewebflux.routes.model.request.RoutesIntegrationRequest;
import com.forttiori.multimodulereactivewebflux.routes.model.response.RoutesIntegrationResponse;

import java.util.List;

public class RouteStub {
    public static final String destination = "Sao Paulo, SP, Brazil";
    public static final String expectedDestination = "São Paulo, SP, Brazil";
    public static final String origin = "Canoas, RS, Brazil";


    public static final Distance distance = Distance.builder()
            .text("1,128 km")
            .value(1127911)
            .build();
    public static final Duration duration = Duration.builder()
            .text("14 hours 3 mins")
            .value(50578)
            .build();
    public static final Element element = Element.builder()
            .distance(distance)
            .duration(duration)
            .status("OK")
            .build();
    public static final List<Element> elements = List.of(element);
    public static final Row row = Row.builder()
            .elements(elements)
            .build();
    public static final List<Row> rows = List.of(row);


    public static RoutesIntegrationRequest routesIntegrationRequest(){

        return RoutesIntegrationRequest.builder()
                .originAddress(origin)
                .destinationAddress(destination)
                .build();
    }

    public static RoutesIntegrationRequest routesIntegrationRequestRequest(){
        return RoutesIntegrationRequest.builder()
                .originAddress(origin)
                .destinationAddress(destination)
                .build();
    }
    public static RoutesIntegrationResponse expectedRouteIntegrationResponse() {

        return RoutesIntegrationResponse.builder()
                .originAddresses(List.of(origin))
                .destinationAddresses(List.of(expectedDestination))
                .rows(rows)
                .status("OK")
                .build();
    }


//    public static RoutesRequest routesRequest(){
//        return RoutesRequest.builder()
//                .originAddress(origin)
//                .destinationAddress(destination)
//                .build();
//    }
//
//    public static TruckEntity truckToSaveWithNewRoute() {
//        return TruckEntity.builder()
//                .id("12321F")
//                .name("Scania")
//                .routesEntity(RoutesEntity.builder()
//                        .originAddress(origin)
//                        .destinationAddress(destination)
//                        .distance(distance.getText())
//                        .duration(duration.getText())
//                        .freightCost(1139.28)
//                        .build())
//                .build();
//    }
//    public static TruckResponse truckWithNewRouteResponse() {
//        return TruckResponse.builder()
//                .id("12321F")
//                .name("Scania")
//                .routesEntity(RoutesEntity.builder()
//                        .originAddress(origin)
//                        .destinationAddress(destination)
//                        .distance(distance.getText())
//                        .duration(duration.getText())
//                        .freightCost(1139.28)
//                        .build())
//                .build();
//    }
//
//    public static TruckResponse truckToSaveWithDeletedRoute() {
//        return TruckResponse.builder()
//                .id("12321F")
//                .name("Scania")
//                .routesEntity(null)
//                .build();
//    }
//
//    public static TruckEntity truckToSaveWithDeletedRouteEntity() {
//        return TruckEntity.builder()
//                .id("12321F")
//                .name("Scania")
//                .routesEntity(null)
//                .build();
//    }



}
