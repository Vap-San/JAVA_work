package JAVA_work.DZ2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.*;

public class app2_2 {
    private static final Logger logger = Logger.getLogger(app2_2.class.getName());

    public static void main(String[] args) throws IOException {
        try {
            FileHandler fh = new FileHandler("log.txt");
            logger.addHandler(fh);
            SimpleFormatter sFormat = new SimpleFormatter();
            fh.setFormatter(sFormat);
        } catch (IOException e) {
            writeLog("Не удалось создать файл лога из-за ошибки ввода-вывода.");
        }

        writeLog("Запуск сортировки методом пузырька");
        int[] array = generateArray(0, 100);
        printArray(array);

        writeLog("Начальный массив:");
        writeLog(Arrays.toString(array));

        sortArray(array);
        writeLog("Отсортированный массив:");
        writeLog(Arrays.toString(array));
    }

    private static int[] generateArray(int min, int max) {
        Random rd = new Random();
        int randomSize = min + rd.nextInt(max);
        System.out.println("Размер массива: " + randomSize);
        int[] array = new int[randomSize];
        for (int i = 0; i < randomSize; i++) {
            array[i] = min + rd.nextInt(max);
        }
        return array;
    }

    public static void sortArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1 + i; j < arr.length; j++) {
                writeLog(Arrays.toString(arr));
                int temp = arr[i];
                if (temp > arr[j]) {
                    int x = arr[j];
                    arr[i] = x;
                    arr[j] = temp;
                }
            }
        }
    }

    public static void printArray(int [] array) {
        System.out.println(Arrays.toString(array));
    }
    
    public static void writeLog(String message) {
        logger.log(Level.INFO, message);
    }
}