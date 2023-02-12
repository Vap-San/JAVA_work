package JAVA_work.DZ1;

import java.util.Scanner;

public class app1 {
    private static Scanner input = new Scanner(System.in);
    

    public static void main(String[] args) {
        int n = getNumberByUser("Введите натуральное N для вычисления n-ого треугольного числа: ");
        System.out.println(print(triangularNumber(n)));
    }

    // Ввод натурального числа и его проверка
    public static int getNumberByUser(String text) {
        int number;
        do {
            System.out.print(text);
            while (!input.hasNextInt()) {
                System.out.print("Некорректные данные, повторите ввод натурального числа! N = ");
                input.next();
            }
            number = input.nextInt();
        } while (number <= 0);
        input.close();
        return number;
    }

    // вычисление треугольного числа
    public static long triangularNumber(int number) {
        return number * (number + 1) / 2;
    }

    // Формирование строки ответа
    public static String print(long number) {
        String output = "Треугольное число: ";
        output += number;
        return output;
    }
}
