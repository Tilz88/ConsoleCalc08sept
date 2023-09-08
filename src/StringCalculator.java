import java.util.Scanner;
import java.util.regex.*;

public class StringCalculator {
    public static String addStrings(String string1, String string2) {
        return string1.replace("\"", "") + string2.replace("\"", "");
    }

    public static String multiplyString(String string, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("число n должно быть целым положительным числом");
        }
        StringBuilder multiplied = new StringBuilder();
        for (int i = 0; i < n; i++) {
            multiplied.append(string);
        }
        return multiplied.toString();
    }

    public static String divideString(String string, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("число n должно быть целым положительным числом");
        }
        int divisionLength = Math.min(string.length(), string.length() / n);
        return string.substring(0, divisionLength);
    }

    public static String subtractString(String baseString, String substring) {
        if (baseString.contains(substring)) {
            String result = baseString.replaceFirst(Pattern.quote(substring), "").trim();
            return result.length() > 40 ? result.substring(0, 40) + "..." : result;
        } else {
            return baseString.length() > 40 ? baseString.substring(0, 40) + "..." : baseString;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        String input = scanner.nextLine().trim();
        scanner.close();

        Pattern pattern = Pattern.compile("^\"(.*?)\"\\s+([-+*/])\\s+(\"?(\\d+|.*?)\"?)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String operand1 = matcher.group(1);
            String operator = matcher.group(2);
            String operand2 = matcher.group(3);

            if (operand1.length() > 10 || operand2.length() > 10) {
                System.out.println("Так нельзя: длина строк не должна превышать 10 символов.");
                return;
            }

            String result;

            if (operator.equals("-")) {
                result = subtractString(operand1, operand2);
            } else if (operator.equals("+")) {
                result = addStrings(operand1, operand2);
            } else if (operator.equals("*")) {
                try {
                    int num = Integer.parseInt(operand2);
                    result = multiplyString(operand1, num);
                } catch (NumberFormatException e) {
                    System.out.println("Числа в таком формате для умножения вводить не стоит.");
                    return;
                }
            } else if (operator.equals("/")) {
                try {
                    int num = Integer.parseInt(operand2);
                    result = divideString(operand1, num);
                } catch (NumberFormatException e) {
                    System.out.println("Числа в таком формате для деления вводить не стоит.");
                    return;
                }
            } else {
                System.out.println("Недопустимый оператор. Поддерживаются операторы  +, -, *, /.");
                return;
            }

            if (result.length() > 40) {
                result = result.substring(0, 40) + "...";
            }

            System.out.println("Результат: " + result);
        } else {
            System.out.println("Неверный формат ввода. Пожалуйста, введите данные в правильном формате.");
        }
    }
}


