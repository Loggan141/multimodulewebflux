
package com.v1.routes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "elements"
})

public class Row {
    @JsonProperty("elements")
    private List<Element> elements;
    @JsonCreator
    public Row(@JsonProperty("elements") List<Element> elements) {
        this.elements = elements;
    }
}
