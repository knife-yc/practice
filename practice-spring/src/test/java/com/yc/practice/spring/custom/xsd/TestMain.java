package com.yc.practice.spring.custom.xsd;

import com.yc.practice.spring.custom.ConsumerProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("custom.xml");
        ConsumerProxy consumerProxy = (ConsumerProxy) applicationContext.getBean("testDemo");
        System.out.println(consumerProxy.getServiceAlias());
    }
}
