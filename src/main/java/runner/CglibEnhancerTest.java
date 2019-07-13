package runner;

import helper.PersonService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;

public class CglibEnhancerTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallback((FixedValue) () -> "yo");
        PersonService personService = (PersonService) enhancer.create();
        System.out.println(personService.getString("not yo"));
        try {
            //casting error due to fixed value for all functions
            System.out.println(personService.lengthOfName("some name"));
        } catch (ClassCastException e) {
            System.err.println("Exception while casting - " + e);
        }
        Enhancer enhancer1 = new Enhancer();
        enhancer1.setSuperclass(PersonService.class);
        enhancer1.setCallback((MethodInterceptor) (obj, method, arg, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class)
                return "yo";
            return proxy.invokeSuper(obj, arg);
        });
        personService = (PersonService) enhancer1.create();
        System.out.println(personService.getString("not yo"));
        System.out.println(personService.lengthOfName("some name"));

    }
}
