package com.yc.practice.test.thread;
//在对象完成之前就将对象作为参数传递给其他的方法或者其他对象作为成员变量时会破坏线程的安全性
//例子：
//资源状态中数据的初始化时nodeInstance在未完成构建之前就添加nodeInstance到NodeFieldInstance对象的引用，
//导致这两个nodeInstance不是同一个对象，后续修改NodeFieldInstance对象的nodeInstance的lastModifyTime时，
//原先的nodeInstance实例lastModifyTime并没有变化
