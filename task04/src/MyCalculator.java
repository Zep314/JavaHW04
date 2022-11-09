import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyCalculator {
    public static String operators = "+-*/^";  // такие операции
    public static String Calculate(String strIncome) {
        List<String> expression = new ArrayList<>(MyParse(strIncome)); // парсим входную строку

//        System.out.println("!"+expression+"!");
//
//        expression = Iterator(expression);
//
//        System.out.println("!"+expression+"!");

        while (expression.size() > 1) {  // должен остаться только один элемент в списке - ответ
            // System.out.println(expression);
            expression = Iterator(expression);
        }
        return expression.get(0);
    }

    public static List MyParse(String string) {  // раскалываем строку на операнды, скобки и числа в список
        List<String> ret = new ArrayList<>();
        StringBuilder num = new StringBuilder("");
        for(char ch: string.toCharArray()) {
            if (".0123456789".contains(String.valueOf(ch))) {
                num.append(ch);
            } else if ( (operators.contains(String.valueOf(ch))) || ("()".contains(String.valueOf(ch)))) {
                if (num.length()>0) {
                    ret.add(String.valueOf(num));
                    num.setLength(0);
                }
                ret.add(String.valueOf(String.valueOf(ch)));
            }
        }
        if (num.length() > 0) {
            ret.add(String.valueOf(num));
        }
        return ret;
    }

    public static String Solver2Operand(List<String> expression) {
        // тут ждем 3 элемента в списке, из которых первый и последний - числа,
        // а средний - операнд (просто операция над 2-мя числами)
        switch (expression.get(1)) {
            case "+":
                return Double.toString(Double.parseDouble(expression.get(0)) + Double.parseDouble(expression.get(2)));
            case "-":
                return Double.toString(Double.parseDouble(expression.get(0)) - Double.parseDouble(expression.get(2)));
            case "*":
                return Double.toString(Double.parseDouble(expression.get(0)) * Double.parseDouble(expression.get(2)));
            case "/":
                return Double.toString(Double.parseDouble(expression.get(0)) / Double.parseDouble(expression.get(2)));
            case "^":
                return Double.toString(Math.pow(Double.parseDouble(expression.get(0)), Double.parseDouble(expression.get(2))));
            default:
                System.out.println("Ошибка в выражении (1)");
                System.exit(-1);
        }
        return "0";
    }

    static public int GetOperatorIndex(List<String> expression, String op) {
        // возвращаем первый сначала списка индекс элемента op
        return expression.indexOf(op);
    }
    public static List Iterator(List<String> expression){
        // тут обрабатываем список элементов выражения
        if (Objects.equals(expression.get(0), "-")) {
            // это если в первое число в выражении отрицательное
            expression.add(0,"0");
        }
        switch (expression.size()) {
            case 0:
                // такого не должно быть, но все же...
                return new ArrayList<>();
            case 1:
                // такого тоже не должно быть, но все же...
                return expression;
            case 2: {
                // и такого тоже не должно быть, но все же...
                List<String> ret1 = new ArrayList<>();
                ret1.add(expression.get(0));
                return ret1;
            }
            case 3: {
                // тут все просто - 3 элемента - просто считаем их
                List<String> ret1 = new ArrayList<>();
                ret1.add(Solver2Operand(expression));
                return ret1;
            }
            default:
                // ищем закр. скобку
                int mypos = GetOperatorIndex(expression, ")");
                if (mypos == -1) {  // скобок нет
                    mypos = GetOperatorIndex(expression, "^"); // ^ - самая высокая приоритет
                    // умножение и деление - равны по приоритету
                    // но, тут вычисляем, кто из */ стоит первым
                    if ((mypos == -1) || ((GetOperatorIndex(expression,"*") < mypos) &&
                            (GetOperatorIndex(expression,"*") > -1 ))) {
                        mypos = GetOperatorIndex(expression,"*");
                    }
                    if ((mypos == -1) || ((GetOperatorIndex(expression,"/") < mypos) &&
                             (GetOperatorIndex(expression,"/") > -1 ))) {
                        mypos = GetOperatorIndex(expression,"/");
                    }
                    // если */^ не нашли - то - или +
                    if (mypos == -1) { mypos = GetOperatorIndex(expression,"-");};
                    if (mypos == -1) { mypos = GetOperatorIndex(expression,"+");};
                    if (mypos > -1) {
                        expression.set(mypos - 1,Solver2Operand(expression.subList(mypos-1,mypos + 2)));
                        // с наивысшим приоритетом
                        // результат пишем в ячейку
                        // первого операнда
                        expression.remove(mypos); // удаляем из списка операцию и второй операнд
                        expression.remove(mypos);
                        return expression;
                    }
                } else {  // обработка скобок
                    int open_bracket = mypos; // ищем откр. скобку - берем сначала позицию закр. скобку
                    for (int i = mypos; i>-1; i--) {  // идем от закр. скобки назад, пока не надем откр. скобку
                        if (Objects.equals(expression.get(i), "(")) {
                            open_bracket = i;  // типа нашли
                            break;
                        }
                    }
                    // делим список на 3 куска
                    // то, что до откр. скобки
                    List<String> expr1 = new ArrayList<>(expression.subList(0,open_bracket));
                    // то, что после закр. скобки
                    List<String> expr3 = new ArrayList<>(expression.subList(mypos+1,expression.size()));
                    // а вот середину без скобок - засовываем
                    // сами в себя (оно там само разберется, что к чему)
                    List<String> expr2 = new ArrayList<>(Iterator(expression.subList(open_bracket + 1, mypos)));

                    expression.clear(); // восстанавливаем наш бедный список
                    if (expr1.size() > 0) { // вдруг впереди только одна скобка и была
                        expression.addAll(expr1);
                    }
                    expression.addAll(expr2);
                    if (expr3.size() > 0) { // вдруг позади только одна скобка и была
                        expression.addAll(expr3);
                    }
                    return expression;
                }

        } // switch
        return expression;
    }

}
