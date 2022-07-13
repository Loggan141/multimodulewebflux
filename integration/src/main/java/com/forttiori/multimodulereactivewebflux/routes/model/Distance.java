
package com.forttiori.multimodulereactivewebflux.routes.model;

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
    "text",
    "value"
})

public class Distance {

    @JsonProperty("text")
    private String text;
    @JsonProperty("value")
    private Integer value;
    @JsonCreator
    public Distance(@JsonProperty("text")String text,
                    @JsonProperty("value")Integer value) {
        this.text = text;
        this.value = value;
    }
}
