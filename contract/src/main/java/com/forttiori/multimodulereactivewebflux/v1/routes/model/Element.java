
package com.forttiori.multimodulereactivewebflux.v1.routes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "distance",
    "duration",
    "status"
})

public class Element {

    @JsonProperty("distance")
    private Distance distance;
    @JsonProperty("duration")
    private Duration duration;
    @JsonProperty("status")
    private String status;
    @JsonCreator
    public Element(@JsonProperty("distance") Distance distance,
                   @JsonProperty("duration") Duration duration,
                   @JsonProperty("status") String status) {
        this.distance = distance;
        this.duration = duration;
        this.status = status;
    }
}
