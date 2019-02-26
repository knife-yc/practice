package com.yc.practice.test.inherit;

/*本包的作用是解释下溯造型（上转型对象的逆转），
要用到下溯造型的场合是本来这个对象是有上转型过来的，但是需要调用衍生类的方法，
这样就需要先判断这个对象是否是需要的对象，如果是则可以强制转换，不是则不行
如果进行强制转换，在编译期间不会报错，但是在运行期会报类型转换错误
*/