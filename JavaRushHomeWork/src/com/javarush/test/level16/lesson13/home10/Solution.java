package com.javarush.test.level16.lesson13.home10;



/* Последовательный вывод файлов
1. Разберись, что делает программа.
2. В статическом блоке считай 2 имени файла firstFileName и secondFileName.
3. Внутри класса Solution создай нить public static ReadFileThread, которая реализует
интерфейс ReadFileInterface (Подумай, что больше подходит - Thread или Runnable).
3.1. Метод setFileName должен устанавливать имя файла, из которого будет читаться содержимое.
3.2. Метод getFileContent должен возвращать содержимое файла.
3.3. В методе run считай содержимое файла, закрой поток. Раздели пробелом строки файла.
4. Подумай, в каком месте нужно подождать окончания работы нити, чтобы обеспечить последовательный вывод файлов.
4.1. Для этого добавь вызов соответствующего метода.
Ожидаемый вывод:
[все тело первого файла]  F:\JAVA\111.txt
[все тело второго файла]  F:\JAVA\222.txt
*/

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public static interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {

        String fileName;

        List<String> list = new ArrayList<>();

        @Override
        public void setFileName(String fullFileName)
        {
            this.fileName = fullFileName;
        }

        @Override
        public  String getFileContent()
        {
            String sss = "";

            for (String lolo : list)
            {
                sss=sss + lolo + " ";
            }

            return sss;
        }

        public void run (){

            String line;
            BufferedReader reader = null;
            try
            {
                FileReader fr= new FileReader(fileName);;
                reader = new BufferedReader(fr);

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            try
            {
                while((line = reader.readLine()) != null)
                {
                    list.add(line);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
