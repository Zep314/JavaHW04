/*
Задание 4. 
Реализовать алгоритм перевода из инфиксной записи в постфиксную для арифметического выражения.
http://primat.org/news/obratnaja_polskaja_zapis/2016-04-09-1181 Вычислить запись если это возможно
Важно! Для вывода используем логгер и соблюдаем код-стайл!
 */

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration( // берем конфиг для логов
                    Main.class.getResourceAsStream("./log.config"));
        } catch (IOException e) {  // печаль, беда...
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        PolishNotation pn = new PolishNotation(); // класс для работы с польской нотацией
        MyCalculator calc = new MyCalculator();   // предыдущая версия отлаженного калькулятора для проверки
        String[] arr = new String[] { // будем решать такое
                "5*6+(2-9)",
                "1+2*(3+5)",
                "-2^5",
                "-2*5",
                "2^5",
                "2+2",
                "1+2*3",
                "1-2*3",
                "1+2*3",
                "(1+2)*3",
                "1/2+1/3",
                "1-22/22-2/2*2+1",
                "15/(7-1+1)+3-2*(4+2)",
                "15/(7*(3+1))*3-(2*(1+1))",
         };

        log.info("Начинаем работу");
        for (String s: arr){
            String polish = pn.Parse(s);  // преобразование в постфиксную строку
            log.info("Преобразование: " + s + " = " + polish);
            log.info("Вычисления:     " + s + " = " + pn.Calculate(polish)); // вычисление постфиксной строки
            log.info("Проверка:       " + s + " = " + calc.Calculate(s));    // вычисление инфиксной строки
                                                                                  // для проверки
            log.info("--------------------------------------");
        }
        log.info("Работа завершена.");
    }
}

/* Вывод программы:
[2022-11-10 00:09:45] [INFO   ] Начинаем работу
[2022-11-10 00:09:45] [INFO   ] Преобразование: 5*6+(2-9) = [5, 6, *, 2, 9, -, +]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     5*6+(2-9) = 23.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       5*6+(2-9) = 23.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 1+2*(3+5) = [1, 2, 3, 5, +, *, +]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     1+2*(3+5) = 17.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       1+2*(3+5) = 17.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: -2^5 = [0, 2, 5, ^, -]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     -2^5 = -32.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       -2^5 = -32.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: -2*5 = [0, 2, 5, *, -]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     -2*5 = -10.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       -2*5 = -10.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 2^5 = [2, 5, ^]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     2^5 = 32.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       2^5 = 32.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 2+2 = [2, 2, +]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     2+2 = 4.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       2+2 = 4.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 1+2*3 = [1, 2, 3, *, +]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     1+2*3 = 7.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       1+2*3 = 7.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 1-2*3 = [1, 2, 3, *, -]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     1-2*3 = -5.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       1-2*3 = -5.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 1+2*3 = [1, 2, 3, *, +]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     1+2*3 = 7.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       1+2*3 = 7.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: (1+2)*3 = [1, 2, +, 3, *]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     (1+2)*3 = 9.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       (1+2)*3 = 9.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 1/2+1/3 = [1, 2, /, 1, 3, /, +]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     1/2+1/3 = 0.8333333333333333
[2022-11-10 00:09:45] [INFO   ] Проверка:       1/2+1/3 = 0.8333333333333333
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 1-22/22-2/2*2+1 = [1, 22, 22, /, -, 2, 2, /, 2, *, -, 1, +]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     1-22/22-2/2*2+1 = -1.0
[2022-11-10 00:09:45] [INFO   ] Проверка:       1-22/22-2/2*2+1 = -1.0
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 15/(7-1+1)+3-2*(4+2) = [15, 7, 1, -, /, 1, +, 3, 2, 4, 2, +, *, -, +]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     15/(7-1+1)+3-2*(4+2) = -5.5
[2022-11-10 00:09:45] [INFO   ] Проверка:       15/(7-1+1)+3-2*(4+2) = -5.5
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Преобразование: 15/(7*(3+1))*3-(2*(1+1)) = [15, 7, 3, 1, +, *, /, 3, *, 2, 1, 1, +, *, -]
[2022-11-10 00:09:45] [INFO   ] Вычисления:     15/(7*(3+1))*3-(2*(1+1)) = -2.392857142857143
[2022-11-10 00:09:45] [INFO   ] Проверка:       15/(7*(3+1))*3-(2*(1+1)) = -2.392857142857143
[2022-11-10 00:09:45] [INFO   ] --------------------------------------
[2022-11-10 00:09:45] [INFO   ] Работа завершена.
 */