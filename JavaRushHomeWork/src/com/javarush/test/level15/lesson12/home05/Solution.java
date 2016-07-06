package com.javarush.test.level15.lesson12.home05;

/* Перегрузка конструкторов
1. В классе Solution создайте по 3 конструктора для каждого модификатора доступа.
2. В отдельном файле унаследуйте класс SubSolution от класса Solution.
3. Внутри класса SubSolution создайте конструкторы командой Alt+Insert -> Constructors.
4. Исправьте модификаторы доступа конструкторов в SubSolution так, чтобы они соответствовали конструкторам класса Solution.
*/

public class Solution {


    private Solution(char a) { }
    private Solution(char c, char b) { }
    private Solution(char c, char d, char e) { }

     Solution(float a, float b, int c) {}
     Solution(int a){ }
     Solution(long b) { }

    protected Solution(float a) {}
    protected Solution(float a, float b) {}
    protected Solution(float a, float b, float c) {}

    public Solution(String s)  { }
    public Solution(String s, int a) {}
    public Solution(String s, float b) { }


}
