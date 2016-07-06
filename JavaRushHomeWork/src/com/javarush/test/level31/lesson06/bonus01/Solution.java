package com.javarush.test.level31.lesson06.bonus01;


import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        args = new String[]{"H:/java/result.mp3", "H:/java/Landscapers – Power Struggle.zip.001",
                "H:/java/Landscapers – Power Struggle.zip.003", "H:/java/Landscapers – Power Struggle.zip.002"};

        List<String> filePaths = new ArrayList<>();
        filePaths.addAll(Arrays.asList(args).subList(1, args.length));
        Collections.sort(filePaths);

        File fileResult = new File(args[0]); // создаем обьект файл для записи результата


        List<File> files = new ArrayList<>();

        for (String filePath : filePaths)
        {
            files.add(new File(filePath)); // записываем объекты File в список, чтобы потом собрать в один архив
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        for (File file : files)
        {
            FileInputStream fis = new FileInputStream(file);
            byte[] buff = new byte[fis.available()];
            while (fis.read(buff) > -1)
            {
                baos.write(buff); //тут мы собираем один архив в поток байтового массива
            }
        }

        OutputStream writer = new FileOutputStream(args[0], true); //

        ZipInputStream zin = new ZipInputStream(new ByteArrayInputStream(baos.toByteArray()));

        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null)
        {
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            byte[] buff = new byte[zin.available()]; // создаем массив байт размером, равным количеству байт в потоке zin

            while (zin.read(buff) != -1) // пока в потоке есть байты
            {
                baos2.write(buff); // записывает все байты в в байтовый поток
            }
            writer.write(baos2.toByteArray()); // записывает байтовый поток в поток записи в конечный результат = разархивированный файл
        }
        writer.close();
        zin.close();
        baos.close();
    }
}
