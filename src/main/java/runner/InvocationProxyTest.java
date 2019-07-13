package runner;

import proxy.InvocationHandlerProxy;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class InvocationProxyTest {

    public static void main(String[] args) throws Exception {
        Map<Integer, String> m = (Map<Integer, String>) Proxy.newProxyInstance(InvocationProxyTest.class.getClassLoader(), new Class[]{Map.class}, new InvocationHandlerProxy(new HashMap<Integer, String>()));
        m.put(1,"b");
        System.out.println(m.get(1));
    }
}
