package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        FileReader frd = new FileReader(bf.readLine());
        FileWriter fwr = new FileWriter(bf.readLine());

        bf.close();

        while (frd.ready())
        {
            int data = frd.read();
            list.add(data);
        }

        for (int i=1; i<list.size(); i++)
        {
            if (i>=list.size()) {break;}
            else
            {
                fwr.write(list.get(i));
                i++;
            }
        }

        frd.close();
        fwr.close();


    }
}
