package com.mutants.mutants.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Data
public class ResponseObject {

    private Integer status;
    private String message;

    /**
     * Function general for create object response dynamic
     * @param status state http for body
     * @param message message custom for body
     */
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
