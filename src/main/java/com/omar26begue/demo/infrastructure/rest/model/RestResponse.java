package com.omar26begue.demo.infrastructure.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "code",
        "message",
        "details",
        "data"
})
public class RestResponse<T> {

    private Integer status;
    private String code;
    private String message;
    private String details;
    private T data;
}
