/*
Задание 4. 
Реализовать алгоритм перевода из инфиксной записи в постфиксную для арифметического выражения.
http://primat.org/news/obratnaja_polskaja_zapis/2016-04-09-1181 Вычислить запись если это возможно
Важно! Для вывода используем логгер и соблюдаем код-стайл!
 */

import java.io.IOException;
import java.util.LinkedList;
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

        PolishNotation pn = new PolishNotation();
        String[] arr = new String[] {
                "5*6+(2-9)",
        };

        log.info("Начинаем работу");
        for (String s: arr){
            String polish = pn.Parse(s);
            log.info("Преобразование: " + s + " = " + polish);
        }
        log.info("Работа завершена.");
    }
}