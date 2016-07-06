package com.javarush.test.level37.lesson10.big01;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet implements Serializable, Cloneable
{

    private static transient final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;


    public AmigoSet()
    {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection)
    {
        int capacity = (int) Math.max(16, collection.size() / .75f);
        this.map = new HashMap<>(capacity);
        this.addAll(collection);
    }

    @Override
    public Iterator<E> iterator()
    {
        return map.keySet().iterator();
    }

    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public boolean isEmpty()
    {
        return map.isEmpty();
    }


    @Override
    public boolean contains(Object o)
    {
        return map.containsKey(o);
    }


    @Override
    public void clear()
    {
        map.clear();
    }

    @Override
    public boolean remove(Object o)
    {

        if (map.containsKey(o))
        {
            map.remove(o);
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Object o)
    {

        if (!map.containsKey(o))
        {
            map.put((E) o, PRESENT);
            return true;
        }
        return false;
    }


    @Override
    public Object clone() throws CloneNotSupportedException
    {
        AmigoSet<E> clone;
        try
        {
            clone = (AmigoSet<E>) super.clone();
            clone.map = (HashMap<E, Object>) map.clone();
        }
        catch (Exception e)
        {
            throw new InternalError();
        }
        return clone;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException
    {
        stream.defaultWriteObject();

        int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");

        stream.writeInt(capacity);
        stream.writeFloat(loadFactor);
        stream.writeInt(map.size());

        for (E e : map.keySet())
        {
            stream.writeObject(e);
        }

    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();

        int capacity = stream.readInt();
        float loadFactor = stream.readFloat();
        int size = stream.readInt();


        map = new HashMap<>(capacity, loadFactor);

        Set set = new HashSet<>();


        for (int i=0; i<size; i++)
        {
            set.add(stream.readObject());
        }

        for (Object o : set)
        {
            map.put((E) o, PRESENT);
        }
    }

}
