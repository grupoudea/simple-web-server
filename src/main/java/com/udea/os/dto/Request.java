package com.udea.os.dto;

public class Request {
    private String method;
    private String path;
    private String base;

    public Request() {
    }

    public Request(String method, String path, String base) {
        this.method = method;
        this.path = path;
        this.base = base;
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

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
