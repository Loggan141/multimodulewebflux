package com.forttiori.multimodulereactivewebflux.routes.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "destination address",
        "origin address"
})

public class RoutesIntegrationRequest {
        
    @JsonProperty("destination address")
    @NotNull
    private String destinationAddress;
    @JsonProperty("origin address")
    @NotNull
    private String originAddress;

}
