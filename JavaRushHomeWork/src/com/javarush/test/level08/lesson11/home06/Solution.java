package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;
import java.util.List;

public class Solution
{


    public static void main(String[] args)
    {
        Human child1 = new Human("сын Денис", true, 24, null); // можно null или ссылку на объект, который всё равно пустой и равен null
        Human child2 = new Human("дочь Анна", false, 20, new ArrayList<Human>());
        Human child3 = new Human("сын Лёня", true, 18, new ArrayList<Human>());

        List<Human> kids = new ArrayList<Human>();
        kids.add(child1);
        kids.add(child2);
        kids.add(child3);

        Human father = new Human("папа Костя", true, 48, kids);
        Human mother = new Human("мама Вера", false, 44, kids);

        List<Human> parents1 = new ArrayList<Human>();
        parents1.add(father);

        List<Human> parents2 = new ArrayList<Human>();
        parents2.add(mother);


        Human grFath1 = new Human("дед Вася", true, 75, parents1);
        Human grFath2 = new Human("дед Петя", true, 73, parents2);

        Human grMoth1 = new Human("баба Зося", false, 74, parents1);
        Human grMoth2 = new Human("баба Дуня", false, 72, parents2);


        System.out.println(grFath1);
        System.out.println(grFath2);
        System.out.println(grMoth1);
        System.out.println(grMoth2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);
    }

    public static class Human
    {
        String name;
        Boolean sex;
        int age;
        List<Human> children;

        public Human(String name, boolean sex, int age, List<Human> children)
        {
            this.name=name;
            this.sex=sex;
            this.age=age;
            this.children=children;
        }


        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }


            return text;
        }
    }

}
