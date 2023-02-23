package JAVA_work.DZ3;

import java.util.Arrays;
import java.io.IOException;
import java.util.Random;
import java.util.logging.*;

public class app3 {
    private static final Logger logger = Logger.getLogger(app3.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler("log.txt");
            logger.addHandler(fh);
            SimpleFormatter sFormat = new SimpleFormatter();
            fh.setFormatter(sFormat);
        } catch (IOException e) {
            writeLog("Не удалось создать файл лога из-за ошибки ввода-вывода.");
        }

        writeLog("Запуск сортировки слиянием");
        int[] array = generateArray(0, 100);
        printArray(array);

        writeLog("Начальный массив:");
        writeLog(Arrays.toString(array));

        long start = System.currentTimeMillis();
        array = mergeSort(array);
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        logger.log(Level.INFO, "Отсортированный массив:");
        logger.log(Level.INFO, Arrays.toString(array));
        logger.log(Level.INFO, "Прошло времени, мс: " + elapsed);
    }

    public static int[] mergeSort(int[] array) {
        int[] temp;
        int[] outArray = array;
        int[] buffer = new int[array.length];
        int size = 1;
        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) {
                merge(outArray, i, outArray, i + size, buffer, i, size);
            }
            temp = outArray;
            outArray = buffer;
            buffer = temp;
            logger.log(Level.INFO, Arrays.toString(outArray));
            size *= 2;
        }
        return outArray;
    }

    

public static void merge(int[] arrayA,  int startA, int[] arrayB, int startB, int[] buffer, int startBuffer, int size) {
    
    int endA = Math.min(startA + size, arrayA.length);
    int endB = Math.min(startB + size, arrayB.length);
    int count = endA - startA + endB - startB;
    int index1 = startA;
    int index2 = startB;

    for (int i = startBuffer; i < startBuffer + count; i++) {
        //условие проверяет три ситуации: обычное сравнение и запись хвостов первого или второго подмассива
        if (index1 < endA && (index2 >= endB || arrayA[index1] < arrayB[index2])) {
            buffer[i] = arrayA[index1]; 
            index1++;
        } else {
            buffer[i] = arrayB[index2];
            index2++;
        }
    }
    }

    private static int[] generateArray(int min, int max) {
        Random rd = new Random();
        int randomSize = min + rd.nextInt(max);
        // int randomSize = 20;
        System.out.println("Размер массива: " + randomSize);
        int[] array = new int[randomSize];
        for (int i = 0; i < randomSize; i++) {
            array[i] = min + rd.nextInt(max);
        }
        return array;
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void writeLog(String message) {
        logger.log(Level.INFO, message);
    }
}
