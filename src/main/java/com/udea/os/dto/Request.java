package com.udea.os.dto;

public class Request {
    private String method;
    private String path;
    private String host;

    public Request() {
    }

    public Request(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public Request(String method, String path, String host) {
        this.method = method;
        this.path = path;
        this.host = host;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
