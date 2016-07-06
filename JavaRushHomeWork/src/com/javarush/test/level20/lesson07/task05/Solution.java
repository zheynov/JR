package com.javarush.test.level20.lesson07.task05;

import java.io.*;

/* Переопределение сериализации
Сделайте так, чтобы после десериализации нить runner продолжила работать.
Ключевые слова объекта runner менять нельзя.
Hint/Подсказка:
Конструктор не вызывается при сериализации, только инициализируются все поля.
*/
public class Solution implements Serializable, Runnable {
    transient private Thread runner;
    private int speed;

    public static void main (String... args) throws IOException, ClassNotFoundException
    {
        FileOutputStream fileOutput = new FileOutputStream("F:/JAVA/111.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        FileInputStream fiStream = new FileInputStream("F:/JAVA/111.txt");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);


        Solution savedObject = new Solution(334);
        outputStream.writeObject(savedObject);
        Solution loadedObject = (Solution) objectStream.readObject();
    }

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        // do something here, does not matter
    }

    /**
     Переопределяем сериализацию.
     Для этого необходимо объявить методы:
     private void writeObject(ObjectOutputStream out) throws IOException
     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, InterruptedException
    {
        in.defaultReadObject();
        runner = new Thread(this);
        runner.start();
}
}
