
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

public class Demo {


    public static void main(String[] args) {
        createTest().calculation(8, 10);
        createTest().calculation(9, 11);
        createTest().print("string");
    }

    static class MyProxy implements InvocationHandler{
        private final Object target;
        private Set<Method> set = new HashSet<>();
        MyProxy(Object target){
            this.target = target;
            findLog();
        }

        void findLog(){
            Method[] methods = target.getClass().getDeclaredMethods();
            for (Method method : methods){
                if (method.isAnnotationPresent(Log.class)){
                    set.add(method);
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method m : set){
                if (m.getName().equals(method.getName())){
                    Parameter[] parameters = m.getParameters();
                    System.out.println("executed method: " + method.getName());
                    for (int i = 0; i < parameters.length; i++){
                        System.out.printf("%s %s = %s \n",
                                parameters[i].getType().getName(),
                                parameters[i].getName(),
                                args[i]);
                    }
                    return method.invoke(target, args);
                }
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
