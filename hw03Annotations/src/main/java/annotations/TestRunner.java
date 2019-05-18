package annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class TestRunner {

    public static void run(Class<?> testClass){

        Method[] testMethods = testClass.getDeclaredMethods();

        List<Method> methodsTest = new PrepareTest().prepare(testMethods, Test.class);
        List<Method> methodsBefore = new PrepareTest().prepare(testMethods, BeforeEach.class);
        List<Method> methodsAfter = new PrepareTest().prepare(testMethods, AfterEach.class);

        for (Method testMethod : methodsTest){
            try {
                Constructor<?> constructor = testClass.getConstructor();
                Object object = constructor.newInstance();
                boolean flag = true;
                for (Method beforeMethod : methodsBefore){
                    try{
                        beforeMethod.invoke(object);
                    }catch (Exception e){
                        flag = false;
                    }
                }
                if (flag)
                    testMethod.invoke(object);

                for (Method afterMethod : methodsAfter){
                    try{
                        afterMethod.invoke(object);
                    }catch (Exception e){

                    }
                }

            }catch(Exception e){

            }
        }
    }
}
