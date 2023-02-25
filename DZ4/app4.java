package JAVA_work.DZ4;

import java.util.Arrays;
import java.io.IOException;
import java.util.Random;
import java.util.logging.*;

public class app4 {
    private static final Logger logger = Logger.getLogger(app4.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler("log.txt");
            logger.addHandler(fh);
            SimpleFormatter sFormat = new SimpleFormatter();
            fh.setFormatter(sFormat);
        } catch (IOException e) {
            writeLog("Не удалось создать файл лога из-за ошибки ввода-вывода.");
        }

        writeLog("Запуск пирамидальной сортировки");
        int[] array = generateArray(0, 100);
        printArray(array);

        writeLog("Начальный массив:");
        writeLog(Arrays.toString(array));

        long start = System.currentTimeMillis();

        pyramidSorting(array, array.length);
        
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        logger.log(Level.INFO, "Отсортированный массив:");
        logger.log(Level.INFO, Arrays.toString(array));
        logger.log(Level.INFO, "Прошло времени, мс: " + elapsed);
    }

    public static void balancingPyramid(int[] array, int iElement, int n) {
        int iChild;
        int tmp = array[iElement];

        if (n == 0) return;
        while (iElement <= n / 2) {
            iChild = 2 * iElement;
            if (iChild < n && array[iChild] < array[iChild + 1]) {
                iChild++;
            }
            if (tmp >= array[iChild]) break;
            array[iElement] = array[iChild];
            iElement = iChild;
        }
        array[iElement] = tmp;
    }

    public static void pyramidSorting(int[] array, int n) {
       
        //Строим пирамиду через восстановление баланса для пирамиды
        for (int i = n / 2; i >= 0; i--) {
            balancingPyramid(array, i, n - 1);
        }
        // Из пирамиды изымается вершина, ставится вначале будущей отсортированной
        // последовательности. На ее место помещается конечный элемент пирамиды и
        // просеивается через нее, занимая положенное ему место. В итоге на вершину
        // восходит снова максимальный элемент. На втором шаге  вершина снова
        // перемещается в конечную последовательность и т.д. до тех пор пока вся
        // пирамида не кончится.
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            balancingPyramid(array, 0, i - 1);
            logger.log(Level.INFO, Arrays.toString(array));
        }
    }

    private static void swap(int[] array, int i, int j) {
        int swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

    private static int[] generateArray(int min, int max) {
        Random rd = new Random();
        int randomSize = min + rd.nextInt(max);
        // int randomSize = 12;
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
