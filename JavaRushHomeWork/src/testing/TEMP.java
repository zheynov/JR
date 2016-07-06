package testing;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TEMP {

    public TEMP() {
    }


    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {



        Constructor[] constructors = TEMP.class.getDeclaredConstructors();

        for (Constructor constr : constructors) {

            if (!constr.isVarArgs()) {

                Object obj = constr.newInstance();

                if (obj instanceof Object) {

                    System.out.println(obj.toString());
                }
            }
        }


    }
}


