package com.yc.practice.test.cglib;

public abstract class AbstractClass {

    private String serviceName;
    private String version;

    public AbstractClass(String serviceName,String version){
        this.serviceName = serviceName;
        this.version = version;
    }

    public void callMethod(){
        System.out.println("method print is called");
    }
}
