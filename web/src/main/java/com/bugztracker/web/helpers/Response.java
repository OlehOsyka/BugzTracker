package com.bugztracker.web.helpers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Yuliia Vovk
 * Date: 19.02.16
 * Time: 16:40
 */
public class Response {

    private Map<String, Object> response;

    public Response(Map<String, Object> response) {
        this.response = new ConcurrentHashMap<>(response);
    }

    public Response() {
        response = new ConcurrentHashMap<>();
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
