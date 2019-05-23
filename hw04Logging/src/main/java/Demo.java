
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;

public class Demo {


    public static void main(String[] args) {
        createTest().calculation(8, 10);
        createTest().calculation(9, 11);
        createTest().print("string");
    }

    static class MyProxy implements InvocationHandler{
        private final Object target;
        MyProxy(Object target){this.target = target;}

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Log.class)){
                Parameter[] parameters = method.getParameters();
                System.out.println("executed method: " + method.getName());
                for (Parameter p : parameters)
                    System.out.println("param: " + p.toString());
                return method.invoke(target, args);
            }
            return null;
        }
    }

    static TestLoggingInterface createTest(){
        InvocationHandler handler = new MyProxy(new TestLogging());
        Class<?>[] classes = new Class[]{TestLoggingInterface.class};
        TestLoggingInterface result = (TestLoggingInterface) Proxy.newProxyInstance(MyProxy.class.getClassLoader(), classes, handler);
        return result;
    }

}
