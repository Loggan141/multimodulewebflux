package com.forttiori.multimodulereactivewebflux.truck.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.forttiori.multimodulereactivewebflux.repository.entity.RoutesEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@With
@Builder
@Data
@JsonPropertyOrder({
        "id",
        "nome",
        "rota"
})
public class TruckServiceRequest {

    @ApiModelProperty(hidden = true)
    @JsonProperty("id")
    private String id;
    @ApiModelProperty(value = "nome", name = "nome", dataType = "String", example = "Scania")
    @JsonProperty("nome")
    @Size(min = 3, max = 15)
    @NotNull
    @NotEmpty
    private String name;
    @ApiModelProperty(hidden = true)
    @JsonProperty("rota")
    private RoutesEntity routesEntity;
    @JsonCreator
    public TruckServiceRequest(@JsonProperty("id")String id,
                               @JsonProperty("nome")String name,
                               @JsonProperty("rota") RoutesEntity routesEntity) {
        this.id = id;
        this.name = name;
        this.routesEntity = routesEntity;
    }
}