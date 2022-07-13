package routes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class RoutesServiceRequest {
//    @ApiModelProperty(value = "destination adress", name = "destino", dataType = "String", example = "Canoas, RS, Brasil")
//    @JsonProperty("destination address")
    @NotNull
    private String destinationAddress;
//    @ApiModelProperty(value = "origin adress", name = "origin", dataType = "String", example = "Porto Alegre, RS, Brasil")
//    @JsonProperty("origin address")
    @NotNull
    private String originAddress;

//    @JsonCreator
//    public RoutesServiceRequest(@JsonProperty("destination address")String destinationAddress,
//                                @JsonProperty("origin address")String originAddress) {
//        this.destinationAddress = destinationAddress;
//        this.originAddress = originAddress;
//    }
}
