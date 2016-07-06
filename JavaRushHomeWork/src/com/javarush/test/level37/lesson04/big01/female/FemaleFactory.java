package com.javarush.test.level37.lesson04.big01.female;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

public class FemaleFactory implements AbstractFactory {

    public Human getPerson(int age) {
        if (age <= 12 && age >= 0)
            return new KidGirl();
        else if (age <= 19 && age > 12)
            return new TeenGirl();
        else
            return new Woman();
    }
}
