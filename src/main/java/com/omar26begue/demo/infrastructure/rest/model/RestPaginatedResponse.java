package com.omar26begue.demo.infrastructure.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customized_offer",
        "page",
        "size",
        "total_element",
        "total_pages",
        "data"
})
public class RestPaginatedResponse<T> {

    private Integer page;
    private Integer size;

    @JsonProperty("total_element")
    private Long totalElement;

    @JsonProperty("total_pages")
    private Integer totalPages;

    private List<T> data;

    @JsonProperty("customized_offer")
    private Integer customizedOffers;
}
