package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
       // args = new String[]{"D:/JAVA/temp/111.txt", "D:/JAVA/111.txt"};

        File file = new File(args[0]);
        File resultFile = new File(args[1]);

        File folder = file.getParentFile();

        Map<String, String> map = new TreeMap<>();

        File[] files = folder.listFiles();

        if (files == null)
        {
            return;
        } else if (files.length == 0)
        {
            folder.delete();
        } else
            search(folder, map);


        BufferedWriter finalResult = new BufferedWriter(new FileWriter(resultFile));

        for (Map.Entry<String, String> entry : map.entrySet())
        {
            BufferedReader fin = new BufferedReader(new FileReader(entry.getValue()));
            while (fin.ready())
            {
                finalResult.write(fin.readLine());
                finalResult.newLine();
            }
            fin.close();
        }
        finalResult.close();

        File newName = new File(resultFile.getParent() + "/allFilesContent.txt");
        resultFile.renameTo(newName);
    }

    public static void search(File file, Map<String, String> map)
    {
        if (file.isDirectory())
        {
            for (File element : file.listFiles())

                if (element.isDirectory())
                {
                    search(element, map);
                    element.delete();

                } else
                {
                    if (element.length() > 50)
                    {
                        element.delete();
                    } else
                    {
                        map.put(element.getName(), element.getAbsolutePath());
                    }
                }
        }
    }
}
