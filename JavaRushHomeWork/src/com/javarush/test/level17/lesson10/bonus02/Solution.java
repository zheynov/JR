package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution
{
    public static List<Person> allPeople = new ArrayList<Person>();

    static
    {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException
    {
        //start here - начни тут

        // args = new String[]{"-i", "1", "0"};
        // args = new String[]{"-d", "0", "1"};
        // args = new String[]{"-c", "Миронов", "м", "15/04/1990", "Сидорова", "ж", "15/04/1985", "Сидорова", "ж", "15/04/1985"};
        // args = new String[]{"-u", "1", "Сидорова", "ж", "15/04/1985", "0", "Миронов", "м", "15/04/1990"};


        if (args[0].equals("-c"))
        {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

            synchronized (allPeople)
            {
                int i = 1;
                while (i < args.length)
                {
                    if (args[i + 1].equals("м"))
                    {
                        allPeople.add(allPeople.size(), Person.createMale(args[i], dateFormat.parse(args[i + 2])));
                    } else
                    {
                        allPeople.add(allPeople.size(), Person.createFemale(args[i], dateFormat.parse(args[i + 2])));
                    }
                    System.out.println(allPeople.size() - 1);

                    i = i + 3;
                }
            }
        } else if (args[0].equals("-u"))
        {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

            synchronized (allPeople)
            {
                int i = 1;
                while (i < args.length)
                {
                    int id = Integer.parseInt(args[i]);

                    if (args[i + 2].equals("м"))
                    {
                        allPeople.get(id).setName(args[i + 1]);
                        allPeople.get(id).setSex(Sex.MALE);
                        allPeople.get(id).setBirthDay(dateFormat.parse(args[i + 3]));
                    } else
                    {
                        allPeople.get(id).setName(args[i + 1]);
                        allPeople.get(id).setSex(Sex.FEMALE);
                        allPeople.get(id).setBirthDay(dateFormat.parse(args[i + 3]));
                    }
                    i = i + 4;
                }
            }
        } else if (args[0].equals("-d"))
        {
            synchronized (allPeople)
            {

                int i = 1;
                while (i < args.length)
                {

                    int id = Integer.parseInt(args[i]);
                    allPeople.get(id).setName("");
                    allPeople.get(id).setSex(null);
                    allPeople.get(id).setBirthDay(null);
                    i++;
                }
            }
        } else if (args[0].equals("-i"))
        {

            synchronized (allPeople)
            {
                int i = 1;
                while (i < args.length)
                {
                    int id = Integer.parseInt(args[i]);

                    String sekas = "";
                    if (allPeople.get(id).getSex() == Sex.MALE)
                    {
                        sekas = "м";
                    } else
                    {
                        sekas = "ж";
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

                    System.out.println(allPeople.get(id).getName() + " " + sekas + " " + dateFormat.format(allPeople.get(id).getBirthDay()));
                    i++;
                }
            }
        }
    }
}