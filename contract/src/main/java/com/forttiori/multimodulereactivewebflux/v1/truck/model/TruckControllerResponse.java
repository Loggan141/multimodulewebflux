package com.forttiori.multimodulereactivewebflux.v1.truck.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.forttiori.multimodulereactivewebflux.repository.entity.RoutesEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonPropertyOrder({
        "id",
        "nome",
        "rota"
})
public class TruckControllerResponse {

    @ApiModelProperty(value = "id", name = "id", dataType = "String", example = "62bdb2d210588a472a1ad288")
    @JsonProperty("id")
    private String id;
    @ApiModelProperty(value = "nome", name = "nome", dataType = "String", example = "Scania")
    @JsonProperty("nome")
    private String name;
    @JsonProperty("rota")
    private RoutesEntity routesEntity;

    @JsonCreator
    public TruckControllerResponse(@JsonProperty("id")String id,
                         @JsonProperty("nome")String name,
                         @JsonProperty("rota") RoutesEntity routesEntity) {
        this.id = id;
        this.name = name;
        this.routesEntity = routesEntity;
    }

}
