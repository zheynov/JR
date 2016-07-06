package com.javarush.test.level31.lesson06.home01;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.
*/
public class Solution
{
    public static void main(String[] args) throws IOException
    {
        args = new String[]{"D:/JAVA/111.txt", "D:/JAVA/test.zip"};

        ZipInputStream zin = null;
        ZipOutputStream zout = null;
        try
        {
            Map<ZipEntry, byte[]> map = new HashMap<>();

            zin = new ZipInputStream(new FileInputStream(args[1]));

            String name = args[0].substring(args[0].lastIndexOf("/") + 1);

            ZipEntry entry;

            while ((entry = zin.getNextEntry()) != null)
            {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buff = new byte[zin.available()];

                int data = zin.read(buff);
                while (data != -1)
                {
                    baos.write(buff);
                }
                map.put(entry, baos.toByteArray());
                zin.closeEntry();
            }


            zout = new ZipOutputStream(new FileOutputStream(args[1]));

            for (Map.Entry<ZipEntry, byte[]> zipEntry : map.entrySet())
            {
                if (!zipEntry.getKey().getName().equals(name))
                {
                    zout.putNextEntry(new ZipEntry(zipEntry.getKey().getName()));
                    zout.write(zipEntry.getValue());
                    zout.closeEntry();
                } else
                {
                    FileInputStream inStream = new FileInputStream(args[0]);
                    byte[] buffer = new byte[inStream.available()];
                    inStream.read(buffer);
                    ZipEntry newEntry = new ZipEntry("new/" + name);

                    zout.putNextEntry(newEntry);
                    zout.write(buffer);
                    zout.closeEntry();
                    inStream.close();
                }
            }
        }

        finally

        {
            zin.close();
            zout.close();
        }
    }
}
