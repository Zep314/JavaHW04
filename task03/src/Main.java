import java.io.IOException;
import java.util.Scanner;

/*
Задание 3. 
В калькулятор добавьте возможность отменить последнюю операцию
 */
public class Main {
    public static void main(String[] args) {
        MyCalculator calc = new MyCalculator();
        //Scanner inp = new Scanner(System.in);
        MyCmdTextEditor cmd = new MyCmdTextEditor();
        String saveString = "";
        String inputString = "";
        cmd.Set("");
        while (true) {

            System.out.print(">>> ");
            try {
                inputString = cmd.Get();
            } catch (IOException e) {
                System.out.printf("Ошибка ввода/вывода: %s\n", e.toString());
            }

            switch (inputString) {
                case "/quit": {
                    //inp.close();
                    System.out.printf("Работа завершена\n");
                    System.exit(-1);
                }
                case "/rollback": {
                    cmd.Set(saveString);
                    break;
                }
                case "/help": {
                    System.out.printf("Программа - калькулятор с командной строкой\n");
                    System.out.printf("Команды:\n");
                    System.out.printf("/quit - выход\n");
                    System.out.printf("/help - вывод помощи\n");
                    System.out.printf("/rollback - отмена последней команды (возвращение ранее введенной команды)\n");
                    System.out.printf("Остальные команды рассматриваются как выражение для вычисления\n");
                    System.out.printf("Например: (1+2)*3\n");
                    System.out.printf("или\n");
                    System.out.printf("15/(7-(1+1))*3-(2+(1+1))\n");
                    break;
                }
                default: {
                    try {
                        saveString = inputString;
                        System.out.printf("Ответ: %s\n", calc.Calculate(inputString.trim()));
                    } catch (Exception e) {
                        System.out.println("Ошибка в выражении.\n Для информации - введите \"/help\"");
                    }
                    //System.out.printf("%s\n",inputString);
                    //System.in
                    break;
                }
            }

        }
    }
}