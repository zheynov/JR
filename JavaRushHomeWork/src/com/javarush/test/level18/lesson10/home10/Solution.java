package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.*;

public class Solution  {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> fileNames = new ArrayList<>();

        while (true)
        {
            String s = reader.readLine();

            if ((s.equals("end")) || (s.isEmpty()))
            {
                break;
            } else
            {
                if (fileNames.contains(s))
                {
                    continue;
                } else

                {
                    fileNames.add(s);
                }
            }
        }

        String name = fileNames.get(1).substring(0, fileNames.get(1).lastIndexOf("."));

        Set<String> sortedSet = new TreeSet<String>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                if ((Integer.parseInt(o1.substring(o1.lastIndexOf("t")+1, o1.length())) > (Integer.parseInt(o2.substring(o2.lastIndexOf("t")+1, o2.length()))))) {
                    return 1; }

                else { return -1; }
            }
        });

        sortedSet.addAll(fileNames);

        OutputStream outputStream = new FileOutputStream(name);

        for (String s : sortedSet)
        {
            byte[] buffer = new byte[1000];
            int count = 0;

            InputStream inStream = new FileInputStream(s);
            while (inStream.available() > 0)
            {
                count = inStream.read(buffer);
                outputStream.write(buffer, 0, count);
            }

            inStream.close();
        }

        outputStream.close();
        reader.close();}
}
