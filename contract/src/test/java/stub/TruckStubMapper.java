package stub;

import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceResponse;
import com.forttiori.multimodulereactivewebflux.v1.truck.model.TruckControllerResponse;

public class TruckStubMapper {

    public static TruckControllerResponse serviceResposeToControllerResponse(TruckServiceResponse truckServiceResponse){
        return TruckControllerResponse.builder()
                .id(truckServiceResponse.getId())
                .routesEntity(truckServiceResponse.getRoutesEntity())
                .name(truckServiceResponse.getName())
                .build();

    }
}
