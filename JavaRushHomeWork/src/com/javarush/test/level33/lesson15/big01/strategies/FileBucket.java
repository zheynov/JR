package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            path = Files.createTempFile(null, null);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() // возвращает размер файла на который указывает path
    {
        return path.toFile().length();
    }

    public void putEntry(Entry entry) // сериализовывавает переданный entry в файл. Каждый entry может содержать еще один entry
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile())))
        {
            oos.writeObject(entry);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry() // забирает entry из файла. Если файл имеет нулевой размер, возвращает null
    {
        File file = path.toFile();
        Entry entry = null;

        if (getFileSize() == 0) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            entry = (Entry) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            ExceptionHandler.log(e);
        }

        return entry;
    }

    public void remove()    // удалять файл на который указывает path
    {
        try
        {
            Files.delete(path);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }
}
