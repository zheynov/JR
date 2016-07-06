package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;

/**
 * Его должен реализовывать каждый класс, который хочет обрабатывать события
 */

public interface EventListener
{
    void move(Direction direction); // передвинуть объект в определенном направлении

    void restart();                 // начать заново текущий уровень

    void startNextLevel();          // начать следующий уровень

    void levelCompleted(int level); // уровень с номером level завершён

}
