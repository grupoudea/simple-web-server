package com.udea.os.config;

public class Properties {
    private String webRoot;
    private String defaultFile;
    private String fileNotFound;
    private String methodNotSupperted;
    private Integer port;
    private Integer maxThreadPool;
    private Integer maxThreadQueue;

    public Properties() {
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }

    public String getDefaultFile() {
        return defaultFile;
    }

    public void setDefaultFile(String defaultFile) {
        this.defaultFile = defaultFile;
    }

    public String getFileNotFound() {
        return fileNotFound;
    }

    public void setFileNotFound(String fileNotFound) {
        this.fileNotFound = fileNotFound;
    }

    public String getMethodNotSupperted() {
        return methodNotSupperted;
    }

    public void setMethodNotSupperted(String methodNotSupperted) {
        this.methodNotSupperted = methodNotSupperted;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getMaxThreadPool() {
        return maxThreadPool;
    }

    public void setMaxThreadPool(Integer maxThreadPool) {
        this.maxThreadPool = maxThreadPool;
    }

    public Integer getMaxThreadQueue() {
        return maxThreadQueue;
    }

    public void setMaxThreadQueue(Integer maxThreadQueue) {
        this.maxThreadQueue = maxThreadQueue;
    }
}
