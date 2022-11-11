import java.util.ArrayList;
import java.util.List;

class PolishNotation {  // Класс для работы с польской записью арифметических выражений
    public static String operators = "^/*-+";  // такие операции по приоритетам
    public static String Parse(String localString) {
        if (localString.charAt(0) == '-') {  // уходим от унарного минуса
            return Parse("0"+localString);  // все выражение вычитаем из 0
        }
        List<String> initList = new ArrayList<>();
        StringBuilder num = new StringBuilder("");
        // преобразуем выражение в список операторов и чисел (пока прямая запись)
        for(char ch: localString.toCharArray()) {
            if (".0123456789".contains(String.valueOf(ch))) {
                num.append(ch);
            } else if (operators.contains(String.valueOf(ch)) || "()".contains(String.valueOf(ch))) {
                if (num.length()>0) {
                    initList.add(String.valueOf(num));
                    num.setLength(0);
                }
                initList.add(String.valueOf(String.valueOf(ch)));
            }
        }
        if (num.length() > 0) {
            initList.add(String.valueOf(num));
        }

        // разворачиваем список в польскую нотацию
        // https://habr.com/ru/post/489744/

        List<String> queue = new ArrayList<>();  // тут все строим
        List<String> stack = new ArrayList<>();  // вспомогательный стек
        for(String s: initList) {
            if (!(operators+"()").contains(s)) {  // если входящий число, то его в очередь
                queue.add(s);
            } else if (operators.contains(s)) {  // если входящий оператор, то
                if (stack.size() == 0 || "(".contains(stack.get(0))) { // если стек пустой, или там ( на 0-м месте
                    if (!s.contains("(")) stack.add(0,s);   // добавляем оператор в стек
                } else if (operators.indexOf(s) < operators.indexOf(stack.get(0))) { // если входящий оператор
                                                                                     // имеет более высокий приоритет
                                                                                     // чем 0-й элемент стека
                    stack.add(0,s);                                            // то добавляем в стек
                } else {  // в противном случае выгружаем стек в очередь пока не увидим оператор с меньшим приоритетом
                          // или скобку (
                    while ((stack.size()>0) && (stack.get(0).contains("(") ||
                            (operators.indexOf(s) >= operators.indexOf(stack.get(0))))) {
                        if ("(".contains(stack.get(0))) {
                            stack.remove(0); // скобки в очередь не грузим
                        } else {
                            queue.add(stack.remove(0));
                        }
                    }
                    stack.add(0,s);
                }
            } else if ("(".contains(s)) {  // если входящий ( - то его в стек
                stack.add(0, s);
            } else if (")".contains(s)) {  // если входящий ) - то выгружаем стек в очередь, пока не найдем (
                while ((stack.size()>0) && (!stack.get(0).contains("("))) {
                    if ("()".contains(stack.get(0))) {
                        stack.remove(0);  // скобки в очередь не грузим
                    } else {
                        queue.add(stack.remove(0));
                    }
                }
                if (stack.size()>0) {
                    stack.remove(0);
                }
            }
        }
        while (stack.size() > 0) {  // остатки из стека выгружаем в очередь
            if ("()".contains(stack.get(0))) {
                stack.remove(0);  // скобки - не грузим
            } else {
                queue.add(stack.remove(0));
            }
        }
        return queue.toString();
    }

    public static String Calculate(String polString) {  // вычисление выражения в польской записи
                                                        // на входе - строка, с разделителями - запятыми
        List<String> queue = new ArrayList<>();
        for(String s: polString
                        .replaceAll("\\[","") //убираем символы [ и ] и делим строку - формируем список
                        .replaceAll("]","")
                        .split(",")) {
            queue.add(s.trim());  // могут попасться пробелы
        }
        List<String> stack = new ArrayList<>(); // тут будем считать, тут же будет и результат
        for(String s: queue) {
            if (!operators.contains(s)) {  // если входящий - число - то в стек его
                stack.add(0, s);
            } else {  // если входящий операнд - то вытаскиваем из стека 2 предыдущих аргумента
                String b = stack.remove(0);
                String a = stack.remove(0);
                String result;
                switch (s) {  // вычисляем элементарную операцию
                    case "^" -> {
                        result = Double.toString(Math.pow(Double.parseDouble(a), Double.parseDouble(b)));
                        break;
                    }
                    case "*" -> {
                        result = Double.toString(Double.parseDouble(a) * Double.parseDouble(b));
                        break;
                    }
                    case "/" -> {
                        result = Double.toString(Double.parseDouble(a) / Double.parseDouble(b));
                        break;
                    }
                    case "+" -> {
                        result = Double.toString(Double.parseDouble(a) + Double.parseDouble(b));
                        break;
                    }
                    case "-" -> {
                        result = Double.toString(Double.parseDouble(a) - Double.parseDouble(b));
                        break;
                    }
                    default -> {  // такого быть не должно, но вдруг...
                        result = "";
                        break;
                    }
                }
                stack.add(0, result);  // результат вычислений - в стек
            }
        }
        return stack.get(0);  // должен остаться только один элемент - ответ
    }
}