package com.javarush.test.level32.lesson06.task01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/* Генератор паролей
Реализуйте логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов
2) только цифры и латинские буквы разного регистра
3) обязательно должны присутствовать цифры, и буквы разного регистра
Все сгенерированные пароли должны быть уникальные.
Пример правильного пароля:
wMh7SmNu
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {

        List<String> buff = new ArrayList<>(8);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        List<Character> alphabet = new ArrayList<>();

        for (char c = 'a'; c <= 'z'; c++) {
            alphabet.add(c);
        }

        for (int i = 0; i < 7; i++) {

            if (i % 2 == 0) {
                buff.add(String.valueOf(alphabet.get((int) (Math.random() * alphabet.size()))));
            } else
                buff.add(String.valueOf(alphabet.get((int) (Math.random() * alphabet.size()))).toUpperCase());
        }

        buff.add(String.valueOf((int) (Math.random() * 10)));

        Collections.shuffle(buff);


        for (String s : buff) {
            try {
                baos.write(s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return baos;
    }
}
