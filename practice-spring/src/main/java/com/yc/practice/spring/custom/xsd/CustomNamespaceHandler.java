package com.yc.practice.spring.custom.xsd;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CustomNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("knife", new CustomBeanDefinitionParser());
    }
}
