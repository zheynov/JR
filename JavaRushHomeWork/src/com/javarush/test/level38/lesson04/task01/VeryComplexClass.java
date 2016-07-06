package com.javarush.test.level38.lesson04.task01;

/* Проверяемые исключения (checked exception)
Напиши реализацию метода veryComplexMethod().
Он должен всегда кидать какое-нибудь проверяемое исключение.
Кинуть исключение (throw) явно нельзя.
*/


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("dfsf")));
        String s = reader.readLine();
    }
}
