/*
Задание 2.
Реализуйте очередь с помощью LinkedList со следующими методами:
enqueue() - помещает элемент в конец очереди,
dequeue() - возвращает первый элемент из очереди и удаляет его,
first() - возвращает первый элемент из очереди, не удаляя.
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

        log.info("Исходный список:");
        log.info(myList.toString());

        log.info("Добавление нового элемента в конец:");
        myList = enqueue(myList, "Одинадцатый");
        log.info(myList.toString());

        log.info("Возвращение первого элемента и удаление его из списка:");
        log.info(dequeue(myList));
        log.info("Список:");
        log.info(myList.toString());

        log.info("Возвращение первого элемента без удаления его из списка:");
        log.info(first(myList));
        log.info("Список:");
        log.info(myList.toString());

        log.info("Работа завершена.");

    }

    public static LinkedList<String> enqueue(LinkedList<String> localList, String newElement) {
        // помещаем новый элемент в конец очереди
        LinkedList<String> ret = (LinkedList<String>) localList.clone();
        ret.addLast(newElement);
        return ret;
    }

    public static String dequeue(LinkedList<String> localList) {
        // Возвращает первоый элемент и удаляет его из списка
        if (localList.size()>0) {
            String ret = localList.get(0);
            localList.remove(0);
            return ret;
        }
        else {
            return "";
        }
    }

    public static String first(LinkedList<String> localList) {
        // Возвращает первоый элемент и не удаляет его из списка
        return (localList.size()>0) ? localList.get(0) : "";
    }
}

/* Вывод программы:
[2022-11-08 23:05:43] [INFO   ] Начинаем работу
[2022-11-08 23:05:44] [INFO   ] Исходный список:
[2022-11-08 23:05:44] [INFO   ] [Первый, Второй, Третий, Четвертый, Пятый, Шестой, Седьмой, Восьмой, Девятый, Десятый]
[2022-11-08 23:05:44] [INFO   ] Добавление нового элемента в конец:
[2022-11-08 23:05:44] [INFO   ] [Первый, Второй, Третий, Четвертый, Пятый, Шестой, Седьмой, Восьмой, Девятый, Десятый, Одинадцатый]
[2022-11-08 23:05:44] [INFO   ] Возвращение первого элемента и удаление его из списка:
[2022-11-08 23:05:44] [INFO   ] Первый
[2022-11-08 23:05:44] [INFO   ] Список:
[2022-11-08 23:05:44] [INFO   ] [Второй, Третий, Четвертый, Пятый, Шестой, Седьмой, Восьмой, Девятый, Десятый, Одинадцатый]
[2022-11-08 23:05:44] [INFO   ] Возвращение первого элемента без удаления его из списка:
[2022-11-08 23:05:44] [INFO   ] Второй
[2022-11-08 23:05:44] [INFO   ] Список:
[2022-11-08 23:05:44] [INFO   ] [Второй, Третий, Четвертый, Пятый, Шестой, Седьмой, Восьмой, Девятый, Десятый, Одинадцатый]
[2022-11-08 23:05:44] [INFO   ] Работа завершена.
 */