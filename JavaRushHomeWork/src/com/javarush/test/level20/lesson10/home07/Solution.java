package com.javarush.test.level20.lesson10.home07;

import java.io.*;

/* Переопределение сериализации в потоке
Сериализация/десериализация Solution не работает.
Исправьте ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution
2) записать в него данные  - writeObject
3) сериализовать класс Solution  - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5
*/
public class Solution implements Serializable, AutoCloseable
{

    transient private FileOutputStream stream;
    private String fileName;

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        FileOutputStream fileOutput = new FileOutputStream("F:/JAVA/111.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        FileInputStream fiStream = new FileInputStream("F:/JAVA/111.txt");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        Solution solution = new Solution("F:/JAVA/222.txt");
        solution.writeObject("kokoko");
        outputStream.writeObject(solution);

        System.out.println(solution.stream);

        Solution newSolution = (Solution) objectStream.readObject();
        newSolution.writeObject("jeejee");
        System.out.println(newSolution.stream);

    }

    public Solution(String fileName) throws FileNotFoundException
    {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }


    public void writeObject(String string) throws IOException
    {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception
    {
        System.out.println("Closing everything!");
        stream.close();
    }
}
