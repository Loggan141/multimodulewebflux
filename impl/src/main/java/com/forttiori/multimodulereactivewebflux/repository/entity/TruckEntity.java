package com.forttiori.multimodulereactivewebflux.repository.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Document(collection = "MultiWebfluxed")
@Data
@Builder
public class TruckEntity {
    @Id
    @JsonProperty("id")
    private String id;
    @Size(min = 3, max = 15)
    @NotEmpty
    @JsonProperty("nome")
    private String name;
    @JsonProperty("rota")
    private RoutesEntity routesEntity;


    @JsonCreator
    public TruckEntity(@JsonProperty("id")String id,
                       @JsonProperty("nome")String name,
                       @JsonProperty("rota") RoutesEntity routesEntity) {
        this.id = id;
        this.name = name;
        this.routesEntity = routesEntity;
    }
}
