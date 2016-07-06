package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        args = new String[]{"-u", "19847983", "fff", "21", "0"};
        //args = new String[]{"-d", "198478"};


        ArrayList<String> strFrmFile = new ArrayList<>();

        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader1.readLine();

        BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader2.readLine()) != null)
        {
            strFrmFile.add(line);
        }

        int size1 = strFrmFile.size();

        String finalString;

        if ((args[0].equals("-u")) && (args.length == 5))
        {

            ArrayList<String> listForAdd = new ArrayList<>();
            int index = 0;

            for (int i = 0; i < strFrmFile.size(); i++)
            {
                String curLime = strFrmFile.get(i).substring(0, 8);

                if ((curLime.contains(" ")) && (!curLime.equals("        ")))
                {
                    index = Integer.parseInt(curLime.substring(0, curLime.indexOf(" ")));
                } else if ((curLime.equals("        ")))
                {
                    listForAdd.add(strFrmFile.get(i));
                } else if (curLime.equals("") != true)
                {
                    index = Integer.parseInt(curLime);
                }

                if (index == Integer.parseInt(args[1]))
                {
                    String args2 = String.format("%.30s", args[2]);
                    String args3 = String.format("%.8s", args[3]);
                    String args4 = String.format("%.4s", args[4]);

                    finalString = String.format("%-8s%-30s%-8s%-4s", args[1], args2, args3, args4);

                    listForAdd.add(finalString);

                } else if (index != Integer.parseInt(args[1]))
                {
                    listForAdd.add(strFrmFile.get(i));
                }
            }

            int size2 = listForAdd.size();

            if ((size1 == size2) && strFrmFile.size() > 0)
            {
                OutputStream fileOutputName = new FileOutputStream(fileName);

                for (int i = 0; i < listForAdd.size() - 1; i++)
                {
                    if (size2 > 1)
                    {
                        fileOutputName.write((listForAdd.get(i) + "\n").getBytes());
                    } else if ((size2 == 0))
                    {
                    }
                }

                if (listForAdd.size() > 0)
                {
                    fileOutputName.write((listForAdd.get(listForAdd.size() - 1)).getBytes());
                }

                fileOutputName.close();
            }
        } else if ((args[0].equals("-d")) || (!args[1].contains("")) || (!args[1].isEmpty()))
        {
            ArrayList<String> listForAdd = new ArrayList<>();
            int index = 0;

            for (int i = 0; i < strFrmFile.size(); i++)
            {
                String curLime = strFrmFile.get(i).substring(0, 8);

                if ((curLime.contains(" ")) && (!curLime.equals("        ")))
                {
                    index = Integer.parseInt(curLime.substring(0, curLime.indexOf(" ")));
                } else if ((curLime.equals("        ")))
                {
                    listForAdd.add(strFrmFile.get(i));
                } else if (curLime.equals("") != true)
                {
                    index = Integer.parseInt(curLime);
                }

                if (index == Integer.parseInt(args[1]))
                {
                } else if (index != Integer.parseInt(args[1]))
                {
                    listForAdd.add(strFrmFile.get(i));
                }
            }

            int size2 = listForAdd.size();

            if (size1 > size2)
            {
                OutputStream fileOutputName = new FileOutputStream(fileName);

                for (int i = 0; i < listForAdd.size() - 1; i++)
                {
                    if (size2 > 1)
                    {
                        fileOutputName.write((listForAdd.get(i) + "\n").getBytes());
                    } else if ((size2 == 0))
                    {
                    }
                }

                fileOutputName.write((listForAdd.get(listForAdd.size() - 1)).getBytes());

                fileOutputName.close();
            }
        }

        reader1.close();
        reader2.close();
    }

}

