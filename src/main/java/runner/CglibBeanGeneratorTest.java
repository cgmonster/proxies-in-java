package runner;

import net.sf.cglib.beans.BeanGenerator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CglibBeanGeneratorTest {

    public static void main(String[] args) {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();
        try {
            Method setter = myBean.getClass().getMethod("setName", String.class);
            setter.invoke(myBean, "some string value set by a cglib");

            Method getter = myBean.getClass().getMethod("getName");
            System.out.println(getter.invoke(myBean));
        } catch (NoSuchMethodException e) {
            System.err.println("Unexpected method invoked - " + e);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("Some other exceptions - " + e + "\n Fooking write the code properly!");
        }
    }
}
