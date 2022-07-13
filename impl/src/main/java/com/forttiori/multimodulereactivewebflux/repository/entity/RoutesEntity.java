package com.forttiori.multimodulereactivewebflux.repository.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@JsonPropertyOrder({
        "origin",
        "destino",
        "distancia",
        "duracao percurso",
        "custo frete"
})
public class RoutesEntity {

    @JsonProperty("origin")
    private String originAddress;
    @JsonProperty("destino")
    private String destinationAddress;
    @JsonProperty("distancia")
    private String distance;
    @JsonProperty("duracao percurso")
    private String duration;
    @JsonProperty("custo frete")
    private double freightCost;
    @JsonCreator
    public RoutesEntity(@JsonProperty("origin") String originAddress,
                        @JsonProperty("destino")String destinationAddress,
                        @JsonProperty("distancia")String distance,
                        @JsonProperty("duracao percurso")String duration,
                        @JsonProperty("custo frete") double freightCost) {
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.distance = distance;
        this.duration = duration;
        this.freightCost = freightCost;
    }
}
