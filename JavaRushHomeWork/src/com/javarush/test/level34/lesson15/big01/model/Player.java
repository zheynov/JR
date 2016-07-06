package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * класс игрока
 */

public class Player extends CollisionObject implements Movable
{

    public Player(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color(255, 255, 0));
        graphics.drawOval(getX(), getY(), getWidth(), getHeight());
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y)
    {
        setX(this.getX() + x);
        setY(this.getY() + y);
    }
}
