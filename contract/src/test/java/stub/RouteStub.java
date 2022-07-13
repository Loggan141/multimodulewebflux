package stub;

import repository.entity.RoutesEntity;
import routes.RoutesServiceRequest;
import truck.model.TruckServiceResponse;
import com.v1.routes.model.Distance;
import com.v1.routes.model.Duration;
import com.v1.routes.model.Element;
import com.v1.routes.model.Row;

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

    public static RoutesServiceRequest routesRequest(){
        return RoutesServiceRequest.builder()
                .originAddress(origin)
                .destinationAddress(destination)
                .build();
    }


    public static TruckServiceResponse truckWithNewRouteResponse() {
        return TruckServiceResponse.builder()
                .id("12321F")
                .name("Scania")
                .routesEntity(RoutesEntity.builder()
                        .originAddress(origin)
                        .destinationAddress(destination)
                        .distance(distance.getText())
                        .duration(duration.getText())
                        .freightCost(1139.28)
                        .build())
                .build();
    }

    public static TruckServiceResponse truckToSaveWithDeletedRoute() {
        return TruckServiceResponse.builder()
                .id("12321F")
                .name("Scania")
                .routesEntity(null)
                .build();
    }



}
