package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

/**
 * - для двух одинаковых строк должен возвращаться один и тот же идентификатор;
 * - он должен поддерживать столько строк, сколько значений может принимать long
 */

public class Shortener
{
    private Long lastId = 0L;                        // последнее значение идентификатора, которое было использовано при добавлении новой строки в хранилище
    private StorageStrategy storageStrategy;         //  в нём хранится стратегия хранения данных

    public Shortener(StorageStrategy storageStrategy)
    {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string)    // возвращает идентификатор id для заданной строки. Значение - сама строка
    {
        if (storageStrategy.containsValue(string))
        {
          return storageStrategy.getKey(string);
        } else
        {
            lastId++;
            storageStrategy.put(lastId, string);
            return lastId;
        }
    }

    public synchronized String getString(Long id)       // возвращает строку для заданного идентификатора или null, если передан неверный идентификатор
    {
        return storageStrategy.getValue(id);
    }
}
