package com.yc.practice.spring.custom.xsd;

import com.yc.practice.spring.custom.ConsumerProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;


public class CustomBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private final Logger logger = LoggerFactory.getLogger(CustomBeanDefinitionParser.class);

    @Override
    protected Class getBeanClass(Element element) {
        return ConsumerProxy.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        try {
            builder.getBeanDefinition().setBeanClass(ConsumerProxy.class);
            //设置创建这个bean的工厂方法，不设置的话则直接走构造方法
            builder.setFactoryMethod("getInstance");
            builder.addConstructorArgValue(element.getAttribute("service"));
            builder.addConstructorArgValue(element.getAttribute("version"));
            builder.addConstructorArgValue(element.getAttribute("serviceAlias"));
            builder.addConstructorArgValue(ClassUtils.forName(element.getAttribute("consumer"), this.getClass().getClassLoader()));
        } catch (ClassNotFoundException e) {
            logger.error("CustomBeanDefinitionParser.doParse ClassNotFoundException.", e);
        }
    }
}
