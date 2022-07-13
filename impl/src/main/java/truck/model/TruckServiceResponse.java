package truck.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import repository.entity.RoutesEntity;

@Builder
@Data
@JsonPropertyOrder({
        "id",
        "nome",
        "rota"
})
public class TruckServiceResponse {
    @ApiModelProperty(value = "id", name = "id", dataType = "String", example = "62bdb2d210588a472a1ad288")
    @JsonProperty("id")
    private String id;
    @ApiModelProperty(value = "nome", name = "nome", dataType = "String", example = "Scania")
    @JsonProperty("nome")
    private String name;
    @JsonProperty("rota")
    private RoutesEntity routesEntity;

    @JsonCreator
    public TruckServiceResponse(@JsonProperty("id")String id,
                                @JsonProperty("nome")String name,
                                @JsonProperty("rota") RoutesEntity routesEntity) {
        this.id = id;
        this.name = name;
        this.routesEntity = routesEntity;
    }
}
