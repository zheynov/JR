package com.javarush.test.level38.lesson10.home02;

public @interface Author {
     String value();
    // коммит прокатил
    Position position() default Position.OTHER;
}
