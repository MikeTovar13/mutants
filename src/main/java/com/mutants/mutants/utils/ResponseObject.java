package com.mutants.mutants.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class ResponseObject {

    private Integer status;
    private String message;

    public ResponseObject(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Map<String, Object> mapObject() {

        Map<String, Object> objectMap = new HashMap<>();

        if (status != null) {
            objectMap.put("status", status);
        }

        if (status != null) {
            objectMap.put("message", message);
        }

        return objectMap;
    }
}
