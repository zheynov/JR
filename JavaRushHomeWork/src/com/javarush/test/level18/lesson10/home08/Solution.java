package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {

    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            String s = reader.readLine();

            if (s.equals("exit"))
            {
                break;
            }
            else
            {
                ReadThread obj = new ReadThread(s);
                obj.start();
                obj.join();
            }
        }

        reader.close();
    }

    public static class ReadThread extends Thread {

        private String nameOfFile;

        public ReadThread(String fileName) {

            this.nameOfFile=fileName;
        }

        public void run ()  {

            Map<Integer, Integer> map = new HashMap<Integer, Integer>();

            ArrayList<Integer> list = new ArrayList<>();

            int maximum = 0;
            int nashbyte = 0;

            try
            {
                InputStream inStream = new FileInputStream(nameOfFile);

                while (inStream.available() > 0)
                {
                    int symb = inStream.read();
                    list.add(symb);
                }

                inStream.close();
            }

            catch (Exception e) {
            }

            for (Integer chr : list)
               {
                   int count = Collections.frequency(list, chr);
                   map.put(chr, count);
               }

            for (Map.Entry<Integer, Integer> pair : map.entrySet())
            {
                if (pair.getValue()>=maximum) {
                    maximum=pair.getValue();
                    nashbyte=pair.getKey();
                }
            }

                resultMap.put(nameOfFile, nashbyte);
        }
    }
}
