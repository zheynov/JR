package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human).
Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/

public class Solution
{
    public static void main(String[] args)
    {
        Human grFatherF = new Human("Пётр", true, 77, null, null);
        Human grFatherM = new Human("Иван", true, 73, null, null);

        Human grMotherF = new Human("Анна", false, 65, null, null );
        Human grMotherM = new Human("Алла", false, 66, null, null );

        Human Father = new Human("Василий", true, 52, grFatherF, grMotherF);
        Human Mother = new Human("Зина", false, 48, grFatherM, grMotherM );

        Human child1 = new Human("Коля", true, 52, Father, Mother);
        Human child2 = new Human("Зоя", true, 52, Father, Mother);
        Human child3 = new Human("Костя", true, 52, Father, Mother);;

        System.out.println(grFatherF);
        System.out.println(grFatherM);
        System.out.println(grMotherF);
        System.out.println(grMotherM);
        System.out.println(Father);
        System.out.println(Mother);
        System.out.println(child3);
        System.out.println(child2);
        System.out.println(child1);
    }

    public static class Human
    {
        String name;
        boolean sex;
        int age;
        Human father;
        Human mother;

        private Human (String name, boolean sex, int age, Human father, Human mother)
        {
            this.name=name;
            this.sex=sex;
            this.age=age;
            this.father=father;
            this.mother=mother;
        }

         public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
               text += ", отец: ";

            if (this.mother != null)
                text += ", мать: ";

            return text;
        }
    }
}
