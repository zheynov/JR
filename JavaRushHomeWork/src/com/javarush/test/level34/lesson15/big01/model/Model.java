package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.nio.file.Paths;

/**
 * Отвечает за модель нашей игры.
 */

public class Model {
    public static final int FIELD_SELL_SIZE = 20; // размер ячейки игрового поля

    private EventListener eventListener;
    private GameObjects gameObjects;              // наши игровые объекты
    private int currentLevel = 1;                 // Поле отвечающее за текущий уровень

    String s = "D:/JAVA/JavaRushHomeWork/src/com/javarush/test/level34/lesson15/big01/res/levels.txt";

    private LevelLoader levelLoader = new LevelLoader(Paths.get(s));  // Поле отвечающие за загрузчик уровней


    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public boolean checkBoxCollision(Direction direction) {

        Player player = gameObjects.getPlayer();

        GameObject object = null;

        for (GameObject gameObject : gameObjects.getAll()) {

            if (!(gameObject instanceof Home) && !(gameObject instanceof Player) && player.isCollision(gameObject, direction)) {
                object = gameObject;
            }
        }

        if (object == null) return false;

        if (object instanceof Box) {
            Box myBox = (Box) object;

            for (Box box : gameObjects.getBoxes()) {
                if (myBox.isCollision(box, direction))
                    return true;
            }

            if (checkWallCollision(myBox, direction))
                return true;

            switch (direction) {
                case DOWN:
                    myBox.move(0, FIELD_SELL_SIZE);
                    break;
                case UP:
                    myBox.move(0, -FIELD_SELL_SIZE);

                    break;
                case LEFT:
                    myBox.move(-FIELD_SELL_SIZE, 0);
                    break;
                case RIGHT:
                    myBox.move(FIELD_SELL_SIZE, 0);
                    break;
            }
        }
        return false;
    }


    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {

        for (Wall wall : gameObjects.getWalls()) {

            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }


    public void move(Direction direction) {

        Player player = getGameObjects().getPlayer();

        if (checkWallCollision(player, direction)) {
            return;
        }

        if (checkBoxCollision(direction)) {
            return;
        }

        if (direction == Direction.DOWN) {
            player.move(0, FIELD_SELL_SIZE);
        } else if (direction == Direction.UP) {
            player.move(0, -FIELD_SELL_SIZE);
        } else if (direction == Direction.RIGHT) {
            player.move(FIELD_SELL_SIZE, 0);
        } else if (direction == Direction.LEFT) {
            player.move(-FIELD_SELL_SIZE, 0);
        }
        checkCompletion();
    }

    public void checkCompletion() {

        int count = 0;

        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if ((box.getX() == home.getX()) && (box.getY() == home.getY())) {
                    count++;
                }
            }
        }
        if (count == getGameObjects().getHomes().size())
            eventListener.levelCompleted(currentLevel);
    }


    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);

    }

    public void restart() {             // перезапуск текущего уровня
        restartLevel(currentLevel);
    }

    public void startNextLevel() {    // запуск нового уровня
        currentLevel++;
        restart();
    }


    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

}
