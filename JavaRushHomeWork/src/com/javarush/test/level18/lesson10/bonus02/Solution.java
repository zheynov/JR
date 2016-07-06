package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

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
    public static void main(String[] args) throws Exception
    {

        if ((args[0].equals("-c")) && (args.length == 4))
        {
            ArrayList<String> strFrmFile = new ArrayList<>();

            BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader1.readLine();

            BufferedReader reader2 = new BufferedReader(new FileReader(fileName));

            String line;

            while ((line = reader2.readLine()) != null)
            {
                strFrmFile.add(line);
            }

            int id = 0;
            int maximim = 0;
            int idTemp = 0;

            for (String s : strFrmFile)
            {
                if (s.isEmpty() != true)

                {
                    String d = s.substring(0, 8);

                    if (d.contains(" "))
                    {
                        idTemp = Integer.parseInt(d.substring(0, s.indexOf(" ")));
                    } else
                    {
                        idTemp = Integer.parseInt(d);
                    }

                    if (maximim < idTemp)
                    {
                        maximim = idTemp;
                    }
                    id = maximim;
                }
            }

            String args0 = String.valueOf(id + 1);

            if (String.valueOf(id).length() < 8)
            {
                int lnt = String.valueOf(id).length();
                for (int i = 0; i < (8 - lnt); i++)
                {
                    args0 = args0 + " ";
                }
            } else
            {
                args0 = String.valueOf(id + 1);
            }

            StringBuilder sb = new StringBuilder();

            String alrgs1 = args[1];
            String alrgs2 = args[2];
            String alrgs3 = args[3];

            if (args[1].length() < 30)
            {

                int lnt = args[1].length();
                for (int i = 0; i < (30 - lnt); i++)
                {
                    alrgs1 = alrgs1 + " ";
                }
            } else if (args[1].length() > 30)
            {
                alrgs1 = args[1].substring(0, 30);
            }

            if (args[2].length() < 8)
            {

                int lnt = args[2].length();
                for (int i = 0; i < (8 - lnt); i++)
                {
                    alrgs2 = alrgs2 + " ";
                }
            } else if (args[2].length() > 8)
            {
                alrgs2 = args[2].substring(0, 8);
            }

            if (args[3].length() < 4)
            {

                int lnt = args[3].length();
                for (int i = 0; i < (4 - lnt); i++)
                {
                    alrgs3 = alrgs3 + " ";
                }
            } else if (args[3].length() > 4)
            {
                alrgs3 = args[3].substring(0, 4);
            }

            sb.append(args0);
            sb.append(alrgs1);
            sb.append(alrgs2);
            sb.append(alrgs3);
            String newElement = sb.toString();

            strFrmFile.add(newElement);

            InputStream inStream = new FileInputStream(fileName);

            byte[] buffer = new byte[1000];
            int count = 0;

            while (inStream.available() > 0)
            {
                count = inStream.read(buffer);
            }

            OutputStream outputStream = new FileOutputStream(fileName);

            if (inStream.available() != 0)
            {
                outputStream.write(buffer, 0, count);
                outputStream.write(("\n" + newElement).getBytes());
            } else
            {

                outputStream.write(newElement.getBytes());
            }

            inStream.close();
            outputStream.close();
            reader1.close();
            reader2.close();
        }
    }
}

