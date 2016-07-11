package com.javarush.test.level33.lesson15.big01.tests;

import com.javarush.test.level33.lesson15.big01.Helper;
import com.javarush.test.level33.lesson15.big01.Shortener;
import com.javarush.test.level33.lesson15.big01.strategies.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest
{
    public void testStorage(Shortener shortener)
    {
        String first = Helper.generateRandomString();
        String second = Helper.generateRandomString();
        String third = first;

        Long id1 = shortener.getId(first);
        Long id2 = shortener.getId(second);
        Long id3 = shortener.getId(third);

        Assert.assertNotEquals(id1, id2);
        Assert.assertNotEquals(id2, id3);
        Assert.assertEquals(id1, id3);

        String res1 = shortener.getString(id1);
        String res2 = shortener.getString(id2);
        String res3 = shortener.getString(id3);

        Assert.assertEquals(res1, first);
        Assert.assertEquals(res2, second);
        Assert.assertEquals(res3, third);
    }

    @Test
    public void testHashMapStorageStrategy()
    {
        HashMapStorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy()
    {
        OurHashMapStorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy()
    {
        FileStorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy()
    {
        DualHashBidiMapStorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy()
    {
        OurHashBiMapStorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy()
    {
        HashBiMapStorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }




}
