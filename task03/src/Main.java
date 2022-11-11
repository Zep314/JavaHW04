import java.util.LinkedList;
import java.util.Scanner;

/*
Задание 3. 
В калькулятор добавьте возможность отменить последнюю операцию
 */
public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        String inputString = "";
        LinkedList<String> myList = new LinkedList<>();  // Создаем список, в нем храним журнал операций

        boolean ex = false;
        while (!ex) {
            System.out.print(">>> ");  // приглашение такое...

            inputString = inp.nextLine();

            switch (inputString) {
                case "/quit" -> {
                    ex = true;
                    break;
                }
                case "/rollback" -> {
                    if (myList.size() > 0) {
                        myList.removeLast();
                        System.out.printf("Запись удалена! В списке осталось %d записей\n", myList.size());
                    } else {
                        System.out.print("Список пуст. Нечего удалять.\n");
                    }
                    break;
                }
                case "/print" -> {
                    if (myList.size() > 0) {
                        for (String s : myList) {
                            System.out.printf("%s\n", s);
                        }
                    } else {
                        System.out.print("Список пуст. Нечего выводить.\n");
                    }
                    break;
                }
                case "/info" -> {
                    System.out.print("Программа - калькулятор с командной строкой\n");
                    System.out.print("Запоминает введенные команды, " +
                            "имеет возможность просмотра и удаления введенных команд\n");
                    System.out.print("Для информации о командах - введите \"/help\"\n");
                    break;
                }
                case "/help" -> {
                    System.out.print("Поддерживаются следующие команды:\n");
                    System.out.print("/quit - выход\n");
                    System.out.print("/help - вывод помощи\n");
                    System.out.print("/info - вывод информации\n");
                    System.out.print("/print - вывод всех ранее введенных выражений\n");
                    System.out.print("/rollback - исключение последней команды из списка\n");
                    System.out.print("Остальные команды рассматриваются как выражение для вычисления\n");
                    System.out.print("Например: (1+2)*3\n");
                    System.out.print("или\n");
                    System.out.print("15/(7-(1+1))*3-(2+(1+1))\n");
                    break;
                }
                default -> {
                    try {
                        String result = MyCalculator.Calculate(inputString.trim());
                        System.out.printf("Ответ: %s\n", result);
                        myList.add(inputString.trim() + " = " + result);
                    } catch (Exception e) {
                        System.out.println("Ошибка в выражении.\n Для информации - введите \"/help\"");
                    }
                    break;
                }
            }
        }
        inp.close();
        System.out.print("Работа завершена\n");
    }
}

/* Вывод программы
>>> /info
Программа - калькулятор с командной строкой
Запоминает введенные команды, имеет возможность просмотра и удаления введенных команд
Для информации о командах - введите "/help"
>>> /help
Поддерживаются следующие команды:
/quit - выход
/help - вывод помощи
/info - вывод информации
/print - вывод всех ранее введенных выражений
/rollback - исключение последней команды из списка
Остальные команды рассматриваются как выражение для вычисления
Например: (1+2)*3
или
15/(7-(1+1))*3-(2+(1+1))
>>> 15/(7-(1+1))*3-(2+(1+1))
Ответ: 5.0
>>> (1+2)*3
Ответ: 9.0
>>> 1-22/22-2/2*2+1
Ответ: -1.0
>>> /print
15/(7-(1+1))*3-(2+(1+1)) = 5.0
(1+2)*3 = 9.0
1-22/22-2/2*2+1 = -1.0
>>> /rollback
Запись удалена! В списке осталось 2 записей
>>> /print
15/(7-(1+1))*3-(2+(1+1)) = 5.0
(1+2)*3 = 9.0
>>> /quit
Работа завершена
 */