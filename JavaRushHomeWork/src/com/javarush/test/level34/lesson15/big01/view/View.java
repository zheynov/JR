package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.Controller;
import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }


    public void update() {  // обновляет представление
        field.repaint();
    }

    public GameObjects getGameObjects() {

        return controller.getGameObjects();
    }

    public void completed(int level) { // сообщает пользователю, что уровень level пройден
        update();
        JOptionPane.showMessageDialog(this, "Уровень " + level + " пройден!");
        controller.startNextLevel();
    }


    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }
}
