/*
Задание 1.
Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список.
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
        //log.setLevel(Level.FINE);
        log.info("Начинаем работу, инициализируем массив случайной величины");

        System.out.println("Hello world!");
        log.info("Работа завершена");
    }
}