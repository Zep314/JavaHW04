/*
Задание 1.
Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список.
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
        log.info("Начинаем работу");

        LinkedList<String> myList = new LinkedList<>();  // Создаем список и заполняем его

        myList.add("Первый");
        myList.add("Второй");
        myList.add("Третий");
        myList.add("Четвертый");
        myList.add("Пятый");
        myList.add("Шестой");
        myList.add("Седьмой");
        myList.add("Восьмой");
        myList.add("Девятый");
        myList.add("Десятый");

        log.info("Изначальный список:");
        log.info(myList.toString());
        log.info("Список после обработки:");
        myList = MyReverseList(myList);  // Нужная обработка
        log.info(myList.toString());
        log.info("Работа завершена.");
    }
    public static LinkedList<String> MyReverseList(LinkedList<String> localList) {
        LinkedList<String> ret = new LinkedList<>();
        while (localList.size()>0) {
            ret.add(localList.pollLast());  // вытаскиваем последний элемент, и удаляем его
        }
        return ret;
    }
}

/* Вывод программы:
[2022-11-08 22:36:58] [INFO   ] Начинаем работу
[2022-11-08 22:36:58] [INFO   ] Изначальный список:
[2022-11-08 22:36:58] [INFO   ] [Первый, Второй, Третий, Четвертый, Пятый, Шестой, Седьмой, Восьмой, Девятый, Десятый]
[2022-11-08 22:36:58] [INFO   ] Список после обработки:
[2022-11-08 22:36:58] [INFO   ] [Десятый, Девятый, Восьмой, Седьмой, Шестой, Пятый, Четвертый, Третий, Второй, Первый]
[2022-11-08 22:36:58] [INFO   ] Работа завершена.
 */