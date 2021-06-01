import com.rpncalculator.token.InvalidTokenException;
import com.rpncalculator.core.Calculator;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] ars) {
        Calculator calculator = new Calculator();
        PrintWriter pw = new PrintWriter(System.out, true);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                calculator.calculate(scanner.nextLine());
            } catch (Exception ex) {
                pw.println(ex.getMessage());
            }
            calculator.printStack(pw);
            pw.println();
        }
    }
}
