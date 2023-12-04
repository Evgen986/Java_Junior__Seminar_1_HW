package ru.maliutin.task1;

import java.util.Arrays;
import java.util.List;

/**
 * Напишите программу,
 * которая использует Stream API для обработки списка чисел.
 * Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */
public class Program {

    public static void main(String[] args) {
        List<Integer> listInt = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Double> listDouble = Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0, 12.0);
        List<Long> listLong = Arrays.asList(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L, 8L);
        System.out.printf("Среднее значение списка ожидается = 6, фактически = %.2f\n", getAverageEvenNum(listInt));
        System.out.printf("Среднее значение списка ожидается = 7, фактически = %.2f\n", getAverageEvenNum(listDouble));
        System.out.printf("Среднее значение списка ожидается = 6.33, фактически = %.2f\n", getAverageEvenNum(listLong));
    }

    public static <T extends Number> double getAverageEvenNum(List<T> list){
        return list.stream()
                .filter(num -> num.doubleValue() % 2 == 0)
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0);
    }
}
