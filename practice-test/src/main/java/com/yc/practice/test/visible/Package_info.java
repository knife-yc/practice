package com.yc.practice.test.visible;

//本包是为了测试java中public、private、default、protected成员的可见性
//结论：
//default成员:在其他包里是不可见的，只要在同包，不论是子类还是其他类，都是可见的，在其他包里，不论是子类还是其他类，都是不可见的
//private成员:只在本类中可见
//public成员：在所有类中可见
//protected成员:在类和子类中可见，其类中不可见