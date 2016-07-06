package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        args = new String[] {"F:/JAVA/111.txt", "F:/JAVA/222.txt" };

        ArrayList<String> result = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String s;

        FileWriter fwr = new FileWriter(args[1]);

        while ((s = reader.readLine()) != null)
        {
            String[] words = s.split(" ");

            for (String word : words) {

                if ((word.replaceAll("[\\D]", "")).isEmpty()!=true) {

                    fwr.write((word + " "));
                }
            }
        }
        reader.close();
        fwr.close();
    }
}
