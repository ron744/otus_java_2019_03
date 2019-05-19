package annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class TestRunner {

    public static void run(Class<?> testClass){

        Method[] testMethods = testClass.getDeclaredMethods();

        List<Method> methodsTest = PrepareTestMethodsHelper.prepare(testMethods, Test.class);
        List<Method> methodsBefore = PrepareTestMethodsHelper.prepare(testMethods, BeforeEach.class);
        List<Method> methodsAfter = PrepareTestMethodsHelper.prepare(testMethods, AfterEach.class);

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
                if (flag) {
                    try{
                        testMethod.invoke(object);
                    }catch(Exception e){

                    }
                }

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
