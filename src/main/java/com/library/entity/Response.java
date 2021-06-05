package com.library.entity;

import lombok.Data;

@Data
public class Response {
    private String response;

    public Response(String s) {
        this.response = s;
    }
}
