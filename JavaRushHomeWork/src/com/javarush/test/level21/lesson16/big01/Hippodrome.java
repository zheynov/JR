package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

public class Hippodrome
{
    public static void main(String[] args) throws InterruptedException
    {
        game = new Hippodrome();

        Horse horse1 = new Horse("Первая лошадь", 3, 0);
        Horse horse2 = new Horse("Вторая лошадь", 3, 0);
        Horse horse3 = new Horse("Третья лошадь", 3, 0);

        game.getHorses().add(horse1);
        game.getHorses().add(horse2);
        game.getHorses().add(horse3);

        game.run();
        game.printWinner();

    }

    public static ArrayList<Horse> horses = new ArrayList<>();

    public static Hippodrome game;

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public void run() throws InterruptedException
    {
        for (int i = 1; i <= 100; i++)
        {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move()
    {
        for (Horse horse : horses)
        {
            horse.move();
        }
    }

    public void print()
    {
        for (Horse horse : horses)
        {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner()
    {
        Horse secretariat=null;
        double max = 0;
        for (Horse horse : horses)
        {

            if (horse.getDistance() > max)
            {
                max = horse.getDistance();
                secretariat = horse;
            }
        }
        return secretariat;
    }

    public void printWinner()
    {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
