import com.yc.practice.socket.tcp.FixedClassLoader;
import com.yc.practice.socket.tcp.Person;
import org.junit.Test;

public class ClassLoaderTest {

    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = new FixedClassLoader();
        Object person = loader.loadClass("com.yc.practice.socket.tcp.Person").newInstance();
        System.out.println(person.getClass());
        System.out.println(person instanceof Person);
    }

    @Test
    public void testFindClass() {
        try {
            FixedClassLoader loader = new FixedClassLoader();
            Class<?> c1 = Class.forName("com.yc.practice.socket.tcp.Person", true, loader);
            Object person = c1.newInstance();
            System.out.println(person.getClass());
            System.out.println(person instanceof Person);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
