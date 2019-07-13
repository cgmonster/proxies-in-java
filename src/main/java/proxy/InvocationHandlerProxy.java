package proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InvocationHandlerProxy implements InvocationHandler {

    Object realObject;
    Map<String, Method> methods = new HashMap<String, Method>();

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public InvocationHandlerProxy(Object realObject) {
        this.realObject = realObject;
        for(Method method : realObject.getClass().getDeclaredMethods()){
            methods.put(method.getName(), method);
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = methods.get(method.getName()).invoke(realObject, args);
        long timeTaken = System.nanoTime() - startTime;
        logger.info("Time taken for executing method {} is {} ns"+method.getName()+ " "+  timeTaken);
        return result;
    }
}
