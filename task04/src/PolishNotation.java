import java.util.ArrayList;
import java.util.List;

class PolishNotation {
    public static String operators = "()^*/+-";  // такие операции
    public static String Parse(String localString) {
        List<String> ret = new ArrayList<>();
        StringBuilder num = new StringBuilder("");
        // преобразуем выражение в список операторов и чисел
        for(char ch: localString.toCharArray()) {
            if (".0123456789".contains(String.valueOf(ch))) {
                num.append(ch);
            } else if (operators.contains(String.valueOf(ch))) {
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

        // разворачиваем список в польскую нотацию

        return ret.toString();
    }
}