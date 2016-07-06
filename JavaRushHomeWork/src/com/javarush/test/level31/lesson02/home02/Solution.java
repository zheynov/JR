package com.javarush.test.level31.lesson02.home02;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution
{
    public static List<String> getFileTree(String root) throws IOException
    {
        Queue<File> queue = new PriorityQueue<>();
        List<String> list = new ArrayList<>();

        File rootFile = new File(root);

        queue.add(rootFile);

        while (queue.size() != 0)
        {
            File element = queue.peek();
            queue.remove(element);

            if (element.isFile())
            {
                list.add(element.getAbsolutePath());
            } else if (element.isDirectory())
            {
                for (File s : element.listFiles())
                {
                    if (s.isFile()) list.add(s.getAbsolutePath());
                    else
                        queue.add(s);
                }
            }
        }
        return list;
    }
}
