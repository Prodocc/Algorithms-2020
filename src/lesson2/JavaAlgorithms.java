package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.util.Arrays;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     * Трудоемкость: O(menNumber)
     * Ресурсоемкость: O(1)
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        int result = 1;
        for (int i = 2; i <= menNumber; i++) {
            result = (choiceInterval + result - 1) % i + 1;
        }
        return result;
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     * Трудоемкость: O(n * m),
     * Ресурсоемкость: O(n * m)
     */
    static public String longestCommonSubstring(String firs, String second) {
        if (firs.isEmpty() || second.isEmpty()) {
            return "";
        }
        int row = firs.length() + 1;
        int column = second.length() + 1;
        int[][] array = new int[row][column];
        for (int i = 1; i < row; i++) {
            array[i][0] = firs.charAt(i - 1);
        }
        for (int j = 1; j < column; j++) {
            array[0][j] = second.charAt(j - 1);
        }
        int max = 0;
        int maxIndexColumn = 0;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (array[0][j] == array[i][0]) {
                    if (i > 1 && j > 1) {
                        array[i][j] = array[i - 1][j - 1] + 1;
                        if (max < array[i][j]) {
                            max = array[i][j];
                            maxIndexColumn = i;
                        }
                    } else {
                        array[i][j] = 1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (max > 0) {
            sb.append((char) array[maxIndexColumn][0]);
            maxIndexColumn--;
            max--;
        }
        sb.reverse();
        return sb.toString();
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     * Трудоемкость: O(n * log(log(n))), n - limit
     * Ресурсоемкость: O(n)
     */
    static public int calcPrimesNumber(int limit) {
        if (limit <= 1) {
            return 0;
        }
        boolean[] array = new boolean[limit + 1];
        Arrays.fill(array, true);
        array[0] = false;
        array[1] = false;
        for (int i = 2; i < array.length; i++) {
            if (array[i]) {
                int j = 2;
                while (j * i < array.length) {
                    array[j * i] = false;
                    j++;
                }
            }
        }
        int result = 0;
        for (boolean elem : array) {
            if (elem) {
                result += 1;
            }
        }
        return result;
    }
}
