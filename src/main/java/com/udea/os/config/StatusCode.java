package com.udea.os.config;

public enum StatusCode {

    HTTP_200(200, "HTTP/1.1 200 OK"),
    HTTP_307(307,"HTTP/1.1 307 Redirect" ),
    HTTP_400(400, "HTTP/1.1 404 File Not Found"),
    HTTP_500(500, "HTTP/1.1 501 Not Implemented");

    private Integer code;
    private String status;

    StatusCode(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
