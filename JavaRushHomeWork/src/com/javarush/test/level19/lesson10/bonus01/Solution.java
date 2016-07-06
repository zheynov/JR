package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution
{
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader fileNames = new BufferedReader(new InputStreamReader(System.in));

        FileReader fr1 = new FileReader(fileNames.readLine());
        FileReader fr2 = new FileReader(fileNames.readLine());

        BufferedReader reader1 = new BufferedReader(fr1);
        BufferedReader reader2 = new BufferedReader(fr2);

        String s;

        List<String> tempFile1 = new ArrayList<>();
        List<String> tempFile2 = new ArrayList<>();

        while ((s = reader1.readLine()) != null)
        {
            tempFile1.add(s);
        }
        while ((s = reader2.readLine()) != null)
        {
            tempFile2.add(s);
        }

        int i = 0;

        while (true)
        {
            if ( i==0 && i == tempFile1.size() && i == tempFile2.size() ) {break;}

            else if (tempFile1.size()>tempFile2.size() && tempFile2.size()==0) {
                lines.add(new LineItem(Type.REMOVED, tempFile1.get(i)));
                tempFile1.remove(tempFile1.get(i));
                i--;
            }

            else if (tempFile1.size()<tempFile2.size() && tempFile1.size()==0) {
                lines.add(new LineItem(Type.ADDED, tempFile2.get(i)));
                tempFile2.remove(tempFile2.get(i));
                i--;
            }


            else if ( tempFile1.get(i).equals(tempFile2.get(i)))
            {
                lines.add(new LineItem(Type.SAME, tempFile1.get(i)));
                tempFile2.remove(tempFile1.get(i));
                tempFile1.remove(tempFile1.get(i));
                i--;
            } else  if (tempFile1.get(i).equals(tempFile2.get(i + 1)))
            {
                lines.add(new LineItem(Type.ADDED, tempFile2.get(i)));
                tempFile2.remove(tempFile2.get(i));
                i--;
            }

            else if ( tempFile1.get(i + 1).equals(tempFile2.get(i)))
            {
                lines.add(new LineItem(Type.REMOVED, tempFile1.get(i)));
                tempFile1.remove(tempFile1.get(i));
                i--;
            }

            else if (tempFile1.contains(tempFile2.get(i))!=true)
            {
                lines.add(new LineItem(Type.ADDED, tempFile2.get(i)));
                tempFile2.remove(tempFile2.get(i));
                i--;
            }

            else  if (tempFile2.contains(tempFile1.get(i))!=true)
            {
                lines.add(new LineItem(Type.REMOVED, tempFile1.get(i)));
                tempFile1.remove(tempFile1.get(i));
                i--;
            }

            else  if ((tempFile1.size()==0)&&(tempFile2.size()>0))

            {
                lines.add(new LineItem(Type.ADDED, tempFile2.get(i)));
                tempFile2.remove(tempFile2.get(i));
                i--;
            }
            else if ((tempFile1.size()>0)&&(tempFile2.size()==0))

            {
                lines.add(new LineItem(Type.REMOVED, tempFile1.get(i)));
                tempFile1.remove(tempFile1.get(i));
                i--;
            }

            i++;
        }

        for (LineItem li : lines)
        {
            System.out.println(li.type.toString() + " " + li.line);
        }


        fileNames.close();
        fr1.close();
        fr2.close();
        reader1.close();
        reader2.close();
    }


    public static enum Type
    {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem
    {
        public Type type;
        public String line;

        public LineItem(Type type, String line)
        {
            this.type = type;
            this.line = line;
        }
    }
}
