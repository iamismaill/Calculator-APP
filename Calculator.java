import java.util.Scanner;

public class CalculatorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the arithmetic expression: ");
            String input = scanner.nextLine();

            Calculator calculator = new Calculator();
            int result = calculator.calculate(input);

            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

class Calculator {

    public int calculate(String input) {
        ArithmeticExpression expression = new ArithmeticExpression(input);
        return expression.evaluate();
    }
}

class ArithmeticExpression {

    private int a;
    private int b;
    private int c;

    public ArithmeticExpression(String input) {
        String[] tokens = input.split("\\s+");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid expression format. Please provide a valid expression.");
        }

        a = InputValidator.parseAndValidateNumber(tokens[0]);
        b = InputValidator.parseAndValidateNumber(tokens[1]);
        c = InputValidator.parseAndValidateNumber(tokens[2]);
    }

    public int evaluate() {
        if (a + b - c >= 1 && a + b - c <= 10) {
            return a + b - c;
        } else if (a - b + c >= 1 && a - b + c <= 10) {
            return a - b + c;
        } else if (a * b - c >= 1 && a * b - c <= 10) {
            return a * b - c;
        } else if (a / b * c >= 1 && a / b * c <= 10) {
            return a / b * c;
        } else {
            throw new IllegalArgumentException("Result is out of valid range (1-10).");
        }
    }
}

class InputValidator {

    public static int parseAndValidateNumber(String token) {
        try {
            int number = Integer.parseInt(token);
            if (number < 1 || number > 10) {
                throw new IllegalArgumentException("Numbers must be between 1 and 10 (inclusive).");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format. Please provide a valid integer.");
        }
    }
}
