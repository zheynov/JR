package com.javarush.test.level32.lesson04.home01;

import java.io.*;

/* Читаем из потока
Реализуйте логику метода getAllDataFromInputStream. Он должен вернуть StringWriter, содержащий все данные из переданного потока.
Возвращаемый объект ни при каких условиях не должен быть null.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("D:\\JAVA\\111.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {

       if (is == null) {
            return new StringWriter();
        }

        StringWriter writer = new StringWriter();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        char[] cbuf = new char[is.available()];
        while (reader.ready()) {
            int length = reader.read(cbuf);
            writer.write(cbuf, 0, length);
        }

        return writer;
    }
}
