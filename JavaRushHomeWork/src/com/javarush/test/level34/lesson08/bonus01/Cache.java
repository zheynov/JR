package com.javarush.test.level34.lesson08.bonus01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V>
{
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception
    {
        //TODO add your code here

        if (!cache.containsKey(key))
        {
            Constructor ccc = (Constructor) clazz.getConstructor(key.getClass());
            cache.put(key, (V) ccc.newInstance(key));
        }
        return cache.get(key);

    }

    public boolean put(V obj)
    {
        //TODO add your code here
        Method method = null;
        try
        {
            method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);
        }
        catch (NoSuchMethodException ignored)
        {
        }
        if (method != null)
        {
            try
            {

                K key = (K) method.invoke(obj);
                cache.put(key, obj);
              return    cache.containsKey(key);
            }
            catch (IllegalAccessException | InvocationTargetException e)
            {
            }
        }
        return false;
    }

    public int size()
    {
        return cache.size();
    }
}
