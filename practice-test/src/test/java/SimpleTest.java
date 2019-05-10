import org.junit.Test;

public class SimpleTest {

    @Test
    public void test() {
        System.out.println(Integer.toBinaryString(((1 << 16) + 1)  >>> 16));
        System.out.println(2  >>> 16);
        System.out.println(Integer.toBinaryString(2  >>> 16));
        System.out.println(A.s.intern() == B.s);
    }

    public static void main(String args[]) {
        String s1 = "abc";//数据创建在常量池中
        String s2 = new String("abc");//查看常量池中有没有这个数据，如果有则在堆中创建这个字符串，没有则在常量池中创建，然后再堆里面创建这个字符串
        String s3 = new String("abc");
        System.out.println(s2 == s1.intern());//intern()方法返回的是常量池里面的引用数据，所以这里返回的是s1自己
        System.out.println(s2 == s3.intern());//intern()方法返回的是常量池里面的引用数据，所以这里返回的是s1
        System.out.println(s1 == s3.intern());//intern()方法返回的是常量池里面的引用数据，所以这里返回的是s1
        System.out.println(s3 == s3.intern());//intern()方法返回的是常量池里面的引用数据，所以这里返回的是s1

        //StringBuilder创建的实例在堆上创建
        String s = new StringBuilder().append("计算机").append("软件").toString();
        System.out.println(s.intern() == s);//true，jdk1.7之后，intern()返回的是字符串在常量池中首次出现的实例引用

        String str1 = new StringBuilder().append("ja").append("va").toString();
        //false,应为方法在执行的时候System类被初始化了，调用了Version方法，里面有java字符串，不符合首次出现的原则
        System.out.println(str1.intern() == str1);

        String str4 = new StringBuilder("haha").toString();
        //false，因为在StringBuilder("haha")中haha字符串已经在常量池中出现了，不符合首次出现原则
        System.out.println(str4 == str4.intern());

        String str5 = new StringBuilder("haha").append("23").toString();
        //true，符合首次出现原则
        System.out.println(str5 == str5.intern());
    }
}
