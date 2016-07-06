package com.javarush.test.level34.lesson15.big01.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Отвечает за сталкивающие объекты
 */

public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) // Возвращает true, если
    // при перемещении текущего объекта в направлении direction на FIELD_SELL_SIZE произойдет столкновение с объектом gameObject
    {
        boolean result = false;

        if (
                (direction == Direction.DOWN && this.getX() == gameObject.getX() && gameObject.getY() == (this.getY() + Model.FIELD_SELL_SIZE)) ||

                (direction == Direction.UP && this.getX() == gameObject.getX() && gameObject.getY() == this.getY() - Model.FIELD_SELL_SIZE) ||

                (direction == Direction.RIGHT && this.getY() == gameObject.getY() && gameObject.getX() == this.getX() + Model.FIELD_SELL_SIZE) ||

                (direction == Direction.LEFT && this.getY() == gameObject.getY() && gameObject.getX() == this.getX() - Model.FIELD_SELL_SIZE))
        {
            result = true;
        }

        return result;
    }

}
