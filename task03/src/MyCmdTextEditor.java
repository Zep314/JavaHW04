import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class MyCmdTextEditor {
    public static StringBuilder data = new StringBuilder("");
    public static int cursor = 0;
    public static int cursorX;
    public static int cursorY;

    public static String keyTyped(KeyEvent e) {
        return String.valueOf(e.getKeyChar());
    }
    public static String Get() throws IOException {
        cursorX = 2;
        cursorY = 2;
        //KeyEvent ke = new KeyEvent();
        Scanner inp = new Scanner(System.in);
//        System.out.print(data.toString());
        String s = inp.nextLine();//data.toString();
        data.setLength(0);
        data.append(s);
        return s;
    }

    public static void Set(String input) {
        data.setLength(0);
        data.append(input);
        cursor = data.length();
    }
}
