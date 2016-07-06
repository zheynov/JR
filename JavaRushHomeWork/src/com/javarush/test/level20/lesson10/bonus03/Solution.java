package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution
{
    public static void main(String[] args)
    {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same", "emoh", "emas", "fderlk", "klredf", "fulmp", "poeejj", "jjeeop",
                "pmluf", "kovhj", "jhvok", "lprr", "rrpl", "lprr", "o", "eo", "oe");

        for (Word element : list)
        {
            System.out.println(element);
        }
/*Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */  }
    public static List<Word> detectAllWords(int[][] crossword, String... words)
    {
        List<Word> list = new ArrayList<>();

        for (String element : words)
        {
            char[] Stroki = element.toCharArray();

            char begin = Stroki[0];
            int startX = 0, startY = 0, endX = 0, endY = 0;

            for (int i = 0; i < crossword.length; i++)
            {
                for (int j = 0; j < crossword[i].length; j++)
                {

                    if (begin == (char) crossword[i][j])
                    {
                        if (FrontLeftToRight(crossword, element, i, j))
                        {
                            startX = j;
                            startY = i;
                            endX = j + element.length() - 1;
                            endY = i;

                        } else if (VerhNiz(crossword, element, i, j))
                        {
                            startX = j;
                            startY = i;
                            endX = j;
                            endY = i + element.length() - 1;

                        } else if (NizVerh(crossword, element, i, j))
                        {
                            startX = j;
                            startY = i;
                            endX = j;
                            endY = i - element.length() + 1;

                        } else if (BackLeftToRight(crossword, element, i, j))
                        {
                            startX = j;
                            startY = i;
                            endX = j - element.length() + 1;
                            endY = i;

                        } else if (diagRtoLUp(crossword, element, i, j))
                        {
                            startX = j;
                            startY = i;
                            endX = j - element.length() + 1;
                            endY = i - element.length() + 1;

                        } else if (diagLeftoRigUp(crossword, element, i, j))
                        {
                            startX = j;
                            startY = i;
                            endX = j + element.length() - 1;
                            endY = i - element.length() + 1;
                        } else if (diagRtoLDown(crossword, element, i, j))
                        {
                            startX = j;
                            startY = i;
                            endX = j - element.length() + 1;
                            endY = i + element.length() - 1;
                        } else if (diagLefttoLRightDown(crossword, element, i, j))
                        {
                            startX = j;
                            startY = i;
                            endX = j + element.length() - 1;
                            endY = i + element.length() - 1;
;
                        }
                    } else continue;
                }
            }
            Word word = new Word(element);
            word.setStartPoint(startX, startY);
            word.setEndPoint(endX, endY);
            list.add(word);
        }
        return list;
    }

    public static boolean FrontLeftToRight(int[][] crossword, String word, int x, int y)
    {
        String s = "";
        int length = word.length();

        for (int i = x; i < x + 1; i++)
        {
            for (int j = y; j < y + length; j++)
            {
                try
                {
                    s = s + (char) crossword[i][j];
                }
                catch (ArrayIndexOutOfBoundsException e){ }
            }
        }
        if (word.equals(s)) return true;
        else
            return false;
    }

    public static boolean VerhNiz(int[][] crossword, String word, int x, int y)
    {
        String s = "";
        int length = word.length();

        for (int i = x; i < x + length; i++)
        {
            for (int j = y; j < y + 1; j++)
            {
                try
                {
                    s = s + (char) crossword[i][j];
                }
                catch (ArrayIndexOutOfBoundsException e) { }
            }
        }
        if (word.equals(s)) return true;
        else
            return false;
    }

    public static boolean BackLeftToRight(int[][] crossword, String word, int x, int y)
    {
        String s = "";
        int length = word.length();

        for (int i = x; i < x + 1; i++)
        {
            for (int j = y; j > y - length; j--)
            {
                try
                {
                    s = s + (char) crossword[i][j];
                }
                catch (ArrayIndexOutOfBoundsException e)  { }
            }
        }
        if (word.equals(s)) return true;
        else
            return false;
    }

    public static boolean NizVerh(int[][] crossword, String word, int x, int y)
    {
        String s = "";
        int length = word.length();

        for (int i = x; i > x - length; i--)
        {
            for (int j = y; j < y + 1; j++)
            {
                try
                {
                    s = s + (char) crossword[i][j];
                }
                catch (ArrayIndexOutOfBoundsException e) {}
            }
        }
        if (word.equals(s)) return true;
        else
            return false;
    }

    public static boolean diagRtoLUp(int[][] crossword, String word, int x, int y)
    {
        String s = "";
        int length = word.length() - 1;

        for (int i = x; i >= x - length; i--)
        {
            for (int j = y; j >= y - length; j--)
            {
                try
                {
                    s = s + (char) crossword[i][j];
                    i--;
                }
                catch (ArrayIndexOutOfBoundsException e) { }
            }
        }
        if (word.equals(s)) return true;
        else
            return false;
    }

    public static boolean diagLeftoRigUp(int[][] crossword, String word, int x, int y)
    {
        String s = "";
        int length = word.length();

        for (int i = x; i > x - length; i--)
        {
            for (int j = y; j < y + length; j++)
            {
                try
                {
                    s = s + (char) crossword[i][j];
                    i--;
                }
                catch (ArrayIndexOutOfBoundsException e) { }
            }
        }
        if (word.equals(s)) return true;
        else
            return false;
    }

    public static boolean diagRtoLDown(int[][] crossword, String word, int x, int y)
    {
        String s = "";

        int length = word.length();

        for (int i = x; i < x + length; i++)
        {
            for (int j = y; j > y - length; j--)
            {
                try
                {
                    s = s + (char) crossword[i][j];
                    i++;
                }
                catch (ArrayIndexOutOfBoundsException e) { }
            }
        }
        if (word.equals(s)) return true;
        else
            return false;
    }

    public static boolean diagLefttoLRightDown(int[][] crossword, String word, int x, int y)
    {
        String s = "";
        int length = word.length();

        for (int i = x; i < x + length; i++)
        {
            for (int j = y; j < y + length; j++)
            {
                try
                {
                    s = s + (char) crossword[i][j];
                    i++;
                }
                catch (ArrayIndexOutOfBoundsException e) { }
            }
        }
        if (word.equals(s)) return true;
        else
            return false;
    }

    public static class Word
    {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text)
        {
            this.text = text;
        }

        public void setStartPoint(int i, int j)
        {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j)
        {
            endX = i;
            endY = j;
        }
        @Override
        public String toString()
        {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
