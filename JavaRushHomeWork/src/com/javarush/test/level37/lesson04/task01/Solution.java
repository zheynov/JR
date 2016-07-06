package com.javarush.test.level37.lesson04.task01;

import java.util.*;

/* Круговой итератор
Класс Solution наследуется от ArrayList.
Напишите свой класс RoundIterator внутри Solution, который будет итератором для списка Solution.
Итератор должен ходить по кругу по всем элементам.
В остальном поведение должно быть идентичным текущему итератору.
*/
public class Solution<T> extends ArrayList<T> {

    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T> {

        int index = 0;

        public boolean hasNext() {

            boolean value = false;

            if (size() > index)
                value = true;
            else if (index == size()) {
                {
                    index = 0;
                    value = true;
                }
            }
            return value;
        }

        public T next() {

            if (size() > index)
                index++;
            else if (size() == index) index = 0;

            return Solution.this.get(index - 1);
        }

        public void remove() {
            Solution.this.remove(index);
        }
    }
}
