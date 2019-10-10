package com.yc.practice.test.lambda;

import org.junit.Test;

public class TestClass {

    @Test
    public void testLambda(){
        //使用lambda表达式实现接口的方法时，接口里面有且只有一个抽象方法，出现多个抽象方法时不能使用lambda的方式实现
        InterfaceA interfaceA = () -> System.out.println("1234");
        interfaceA.testInterface();
    }
}
