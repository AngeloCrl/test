package com.assignment.app.rest.utils.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDataWrapper<T> {
    private T data;
}