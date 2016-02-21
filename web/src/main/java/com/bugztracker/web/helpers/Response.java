package com.bugztracker.web.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Yuliia Vovk
 * Date: 19.02.16
 * Time: 16:40
 */
public class Response {

    private Map<String, Object> response;

    public Response(Map<String, Object> response) {
        this.response = new HashMap<>(response);
    }

    public Response() {
        response = new HashMap<>();
    }

    public void add(String key, Object value) {
        response.put(key, value);
    }

    public boolean contains(String key) {
        return response.containsKey(key);
    }

    public Object get(String key) {
        return response.get(key);
    }

    public Map<String, Object> getResponse() {
        return response;
    }
}
