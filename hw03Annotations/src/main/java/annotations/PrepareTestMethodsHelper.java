package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class PrepareTestMethodsHelper {

    private PrepareTestMethodsHelper(){}

    public static List<Method> prepare(Method[] methods, Class<?> clazz){

        List<Method> done = new ArrayList<>();
        for (int i = 0; i < methods.length; i++){
            if(methods[i].isAnnotationPresent((Class<? extends Annotation>) clazz)){
                System.out.println(methods[i]);
                done.add(methods[i]);
            }
        }
        return done;
    }
}
