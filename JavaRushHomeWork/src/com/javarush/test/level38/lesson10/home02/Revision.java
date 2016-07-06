package com.javarush.test.level38.lesson10.home02;


public @interface Revision {
    //напиши свой код
    Date date();
    int revision();
    String comment() default "";
    Author[] authors() default {};
}
