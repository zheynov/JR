package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 *  Стена
 */

public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(new Color(139, 69, 19)); //         graphics.setColor(Color.ORANGE);
        graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        graphics.fillRect(getX()+1, getY()+1, getWidth()-1, getHeight()-1);

    }
}
