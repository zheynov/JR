package com.javarush.test.level34.lesson15.big01.model;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * Загрузчик уровней
 */

public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels) // Путь к тестовому файлу, содержащему описание уровней
    {
        this.levels = levels;
    }

    public GameObjects getLevel(int level)
    {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        while (level - 60 > 0)
        {
            level = level - 60;
        }

        ArrayList<String> allLevelsData = new ArrayList<>(); // тут все строки из файла levels
        ArrayList<String> levelData = new ArrayList<>(); // сюда загружены только строки с уровнем

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toString())))
        {
            String s;

            while ((s = reader.readLine()) != null)
            {
                allLevelsData.add(s);
            }
        }
        catch (IOException ignored)
        {

        }

        int firstIndex = 0, secondIndex = 0;
        for (int i = 0; i < allLevelsData.size(); i++)
        {
            if (allLevelsData.get(i).endsWith("Maze: " + level)) firstIndex = i;

            if (allLevelsData.get(i).endsWith("Maze: " + (level + 1))) secondIndex = i;
        }

        for (int i = firstIndex + 7; i < secondIndex - 2; i++)
        {
            levelData.add(allLevelsData.get(i));
        }

        int y0 = Model.FIELD_SELL_SIZE / 2;

        for (String s : levelData)
        {
            int x0 = Model.FIELD_SELL_SIZE / 2;

            char[] mass = s.toCharArray();

            for (char mas : mass)
            {
                switch (mas)
                {
                    case ('X'):
                        walls.add(new Wall(x0, y0));
                        break;
                    case ('*'):
                        boxes.add(new Box(x0, y0));
                        break;
                    case ('@'):
                        player = new Player(x0, y0);
                        break;
                    case ('.'):
                        homes.add(new Home(x0, y0));
                        break;
                    case ('&'):
                        boxes.add(new Box(x0, y0));
                        homes.add(new Home(x0, y0));
                        break;
                    default:
                        break;
                }
                x0 = x0 + Model.FIELD_SELL_SIZE;
            }
            y0 = y0 + Model.FIELD_SELL_SIZE;
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
