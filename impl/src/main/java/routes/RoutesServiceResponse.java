package routes;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoutesServiceResponse {

    private String originAddress;
    private String destinationAddress;
    private String distance;
    private String duration;
    private double freightCost;

}
