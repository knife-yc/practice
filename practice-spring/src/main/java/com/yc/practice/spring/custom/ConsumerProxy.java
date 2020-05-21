package com.yc.practice.spring.custom;

public class ConsumerProxy {

    private String service;
    private String version;
    private Class<?> consumer;
    private String serviceAlias;

    public ConsumerProxy(String service, String version, String serviceAlias, Class<?> consumer) {
        this.service = service;
        this.version = version;
        this.serviceAlias = serviceAlias;
        this.consumer = consumer;
    }

    public static ConsumerProxy getInstance(String service, String version, String serviceAlias, Class<?> consumer) {
        return new ConsumerProxy(service, version, serviceAlias, consumer);
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Class<?> getConsumer() {
        return consumer;
    }

    public void setConsumer(Class<?> consumer) {
        this.consumer = consumer;
    }

    public String getServiceAlias() {
        return serviceAlias;
    }

    public void setServiceAlias(String serviceAlias) {
        this.serviceAlias = serviceAlias;
    }
}
