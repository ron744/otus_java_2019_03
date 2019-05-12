package annotations;

import java.lang.reflect.Method;

public class TestRunner {

    public static void main(String[] args){
        run(AnnotationsTest.class);
    }

    private static void run(Class<?> testClass){

        Method[] methods = testClass.getDeclaredMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(BeforeEach.class)){
                try {
                    method.invoke(testClass);
                }catch (Exception e){
                }
            }
        }
        for(Method method : methods){
            if(method.isAnnotationPresent(Test.class)){
                try {
                    method.invoke(testClass);
                }catch (Exception e){
                }
            }
        }
        for(Method method : methods){
            if(method.isAnnotationPresent(AfterEach.class)){
                try {
                    method.invoke(testClass);
                }catch (Exception e){
                }
            }
        }
    }
}
