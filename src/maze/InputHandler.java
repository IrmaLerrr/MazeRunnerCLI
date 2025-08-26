package maze;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getOption(int min, int max, String msg){
        int num = getNum(min, max, msg);
        scanner.nextLine();
        return num;
    }

    public static int getNum(int min, int max, String msg){
        while (true) {
            try {
                int num = scanner.nextInt();
                if (num < min || num > max) throw new InputMismatchException();
                return num;
            } catch (InputMismatchException ex) {
                scanner.nextLine();
                System.out.println(msg);
            }
        }
    }

    public static String getFilePath(){
        return scanner.nextLine();
    }
}
