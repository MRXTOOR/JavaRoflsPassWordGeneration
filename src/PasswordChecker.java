import java.util.Scanner;
import java.util.regex.Pattern;

public class PasswordChecker {
    private static final int MIN_LENGTH = 8;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]");
    private static final Pattern SYMBOL_PATTERN = Pattern.compile("[!@#$%^&*()_+=\\-\\[\\]{}|;':\"\\\\,./<>?]");
    private static final Pattern REPEATING_PATTERN = Pattern.compile(".*(.)\\1\\1.*");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = "";
        boolean keepEnteringPassword = true;

        while (keepEnteringPassword) {
            while (!validatePassword(password)) {
                System.out.print("Введите пароль: ");
                password = scanner.nextLine();

                if (!validatePassword(password)) {
                    System.out.println("Пароль недопустим. Проверьте следующие правила:");
                    System.out.println("- Пароль должен содержать минимум " + MIN_LENGTH + " символов");
                    System.out.println("- Пароль не должен содержать пробелы");
                    System.out.println("- Пароль должен содержать по крайней мере одну заглавную и одну строчную букву");
                    System.out.println("- Пароль должен содержать по крайней мере одну цифру");
                    System.out.println("- Пароль должен содержать по крайней мере один символ из списка !@#$%^&*()_+-=[]{}|;':\"\\\\,./<>?");

                    if (REPEATING_PATTERN.matcher(password).matches()) {
                        System.out.println("- Пароль не должен содержать повторяющиеся символы или последовательности символов");
                    }
                }
            }

            if (validatePassword(password)) {
                System.out.println("Пароль надежный");
            } else {
                System.out.println("Пароль ненадежный");
            }

            System.out.print("Хотите ввести новый пароль? (y/n): ");
            String choice = scanner.nextLine();
            while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
                System.out.println("Неверный выбор. Введите 'y' или 'n'");
                System.out.print("Хотите ввести новый пароль? (y/n): ");
                choice = scanner.nextLine();
            }

            if (choice.equalsIgnoreCase("n")) {
                keepEnteringPassword = false;
                System.out.println("Спасибо за использование!");
            } else {
                password = "";
            }
        }
    }

    private static boolean validatePassword(String password) {
        if (password.length() < MIN_LENGTH) {
            return false;
        }

        if (password.contains(" ")) {
            return false;
        }

        if (!UPPERCASE_PATTERN.matcher(password).find() || !LOWERCASE_PATTERN.matcher(password).find()
                || !DIGIT_PATTERN.matcher(password).find() || !SYMBOL_PATTERN.matcher(password).find()) {
            return false;
        }

        return !REPEATING_PATTERN.matcher(password).matches();
    }
}