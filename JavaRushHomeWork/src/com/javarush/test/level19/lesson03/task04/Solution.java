package com.javarush.test.level19.lesson03.task04;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

public class Solution {
    public static class PersonScannerAdapter implements PersonScanner{

        private Scanner scanner;

        public PersonScannerAdapter(Scanner scanner)
        {
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException
        {
            Person person = null;

            String s="";
            if(scanner.hasNextLine())
            {
                s = scanner.nextLine();

                String s1 = s.split(" ")[0];
                String s2 = s.split(" ")[1];
                String s3 = s.split(" ")[2];
                String s4 = s.split(" ")[4] +"/"+ s.split(" ")[3] +"/"+ s.split(" ")[5];

                person =  new Person(s2, s3, s1, new Date(s4));
            }

            return  person;
        }

        @Override
        public void close() throws IOException
        {
            scanner.close();
        }
    }
}
