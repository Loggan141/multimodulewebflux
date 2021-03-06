package stub;

import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceRequest;
import com.forttiori.multimodulereactivewebflux.truck.model.TruckServiceResponse;

public class TruckStub {

    public static TruckServiceRequest truckToCreate(){
        return TruckServiceRequest.builder()
                .id("12321F")
                .name("Scania")
                .build();
    }

    public static TruckServiceResponse expectedTruckToReturn(){
       return   TruckServiceResponse.builder()
                .id("12321F")
                .name("Scania")
                .build();
    }

     public static TruckServiceResponse expectedUpdatedTruckToReturn(){
       return   TruckServiceResponse.builder()
                .id("12321F")
                .name("ScaniaDois")
                .build();
    }

     public static TruckServiceRequest truckToUpdate(){
           return  TruckServiceRequest.builder()
                    .id("12321F")
                    .name("Scania")
                    .build();
        }


}
