package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        //args = new String[]{"F:/JAVA/111.txt"};

        ArrayList<Character> list = new ArrayList<>();
        char[] alph = {
                'a' , 'b' , 'c' , 'd' , 'e' , 'f' ,
                'g' , 'h' , 'i' , 'j' , 'k' , 'l' ,
                'm' , 'n' , 'o' , 'p' , 'q' , 'r' ,
                's' , 't' , 'u' , 'v' , 'w' , 'x' ,
                'y' , 'z' , 'A' , 'B' , 'C' , 'D' ,
                'E' , 'F' , 'G' , 'H' , 'I' , 'J' ,
                'K' , 'L' , 'M' , 'N' , 'O' , 'P' ,
                'Q' , 'R' , 'S' , 'T' , 'U' , 'V' ,
                'W' , 'X' , 'Y' , 'Z'     };

        for (int i=0; i<alph.length; i++)
        {
            list.add(alph[i]);
        }

        InputStream inStream = new FileInputStream(args[0]);

        int count = 0;
        while (inStream.available() > 0)
        {
            char symb = (char) inStream.read();
            if (list.contains(symb))
                count++;
        }

        System.out.println(count);
        inStream.close();
    }
}
