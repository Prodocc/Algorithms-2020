package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Трудоемкость O(m*n)
     * Ресурсоемкость O(m*n)
     */
    public static String longestCommonSubSequence(String first, String second) {
        int m = first.length();
        int n = second.length();
        int[][] table = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (first.charAt(i) == second.charAt(j)) {
                    table[i][j] = table[i + 1][j + 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i + 1][j], table[i][j + 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (first.charAt(i) == second.charAt(j)) {
                sb.append(first.charAt(i));
                i++;
                j++;
            } else if (table[i + 1][j] >= table[i][j + 1]) {
                i++;
            } else {
                j++;
            }
        }
        return sb.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     * Трудоемкость O(n * logn)
     * Ресурсоемкость O(n^2)
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int[] arr = list.stream().mapToInt(i -> i).toArray();
        int[][] outputArr = new int[arr.length][2];
        int max = 0;
        for (int i = 0; i < arr.length; ++i) {
            outputArr[i][0] = -1;
            outputArr[i][1] = 1;
            for (int j = i - 1; j >= 0; --j) {
                if (arr[i] > arr[j]) {
                    if (outputArr[i][1] <= outputArr[j][1] + 1) {
                        outputArr[i][1] = outputArr[j][1] + 1;
                        outputArr[i][0] = j;
                    }
                }
            }
            max = Math.max(max, outputArr[i][1]);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; ++i) {
            if (outputArr[i][1] == max) {
                int current = i;
                while (current != -1) {
                    result.add(arr[current]);
                    current = outputArr[current][0];
                }
                break;
            }
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
