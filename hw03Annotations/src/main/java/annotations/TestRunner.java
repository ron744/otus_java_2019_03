package annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestRunner {

    public static void main(String[] args){
        run(AnnotationsTest.class);
    }

    private static void run(Class<?> testClass){

        Method[] testMethods = testClass.getDeclaredMethods();
        for(Method testMethod : testMethods){
            if(testMethod.isAnnotationPresent(Test.class)){
                try {
                    Constructor<?> constructor = testClass.getConstructor();
                    Object object = constructor.newInstance();
                    Method[] beforeMethods = testClass.getDeclaredMethods();
                    boolean flag = true;
                    for(Method beforeMethod : beforeMethods){
                        if(beforeMethod.isAnnotationPresent(BeforeEach.class)){
                            try {
                                beforeMethod.invoke(object);
                            }catch (Exception e){
                                flag = false;
                            }
                        }
                    }
                    if (flag)
                        testMethod.invoke(object);

                    Method[] afterMethods = testClass.getDeclaredMethods();
                    for(Method afterMethod : afterMethods){
                        if(afterMethod.isAnnotationPresent(AfterEach.class)){
                            try {
                                afterMethod.invoke(object);
                            }catch (Exception e){
                            }
                        }
                    }

                }catch (Exception e){
                }
            }
        }
    }
}
