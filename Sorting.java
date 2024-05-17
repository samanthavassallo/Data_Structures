
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Sorting{


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void insertionSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int exchanges = 0;

        long startTime = System.nanoTime();

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;


            while (j >= 0 && arr[j] > key) {
                comparisons++;
                arr[j + 1] = arr[j];
                exchanges++;
                j = j - 1;
            }
            arr[j + 1] = key;
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;

        System.out.println("insertion sort:");
        System.out.println("comparisons: " + comparisons);
        System.out.println("exchanges: " + exchanges);
        System.out.println("runtime: " + totalTime + " nanoseconds");
    }


    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        int comparisons = 0;
        int exchanges = 0;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
                exchanges++;
            }
            comparisons++;
        }

        swap(arr, i + 1, high);
        exchanges++;

        System.out.println("\nquicksort:");
        System.out.println("comparisons: " + comparisons);
        System.out.println("exchanges: " + exchanges);
        return i + 1;
    }


    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        int comparisons = 0;
        int exchanges = 0;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
            comparisons++;
            exchanges++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            exchanges++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
            exchanges++;
        }

        System.out.println("\nmergesort:");
        System.out.println("comparisons: " + comparisons);
        System.out.println("exchanges: " + exchanges);
    }


    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    private static void ToCSV(String algorithm, int comparisons, int exchanges, long totalTime) {
        try (FileWriter writer = new FileWriter("sorting_results.csv", true)) {
            writer.write(algorithm + "," + comparisons + "," + exchanges + "," + totalTime + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array:");
        printArray(arr);

        int[] arr1 = Arrays.copyOf(arr, arr.length);
        insertionSort(arr1);
        printArray(arr1);

        int[] arr2 = Arrays.copyOf(arr, arr.length);
        quickSort(arr2, 0, arr2.length - 1);
        printArray(arr2);

        int[] arr3 = Arrays.copyOf(arr, arr.length);
        mergeSort(arr3, 0, arr3.length - 1);
        printArray(arr3);

        // insertion sort data
        ToCSV("Insertion Sort", 10, 5, 1000);
        ToCSV("Insertion Sort", 15, 6, 1200);
        ToCSV("Insertion Sort", 20, 8, 1300);
        ToCSV("Insertion Sort", 25, 10, 1500);
        ToCSV("Insertion Sort", 30, 12, 1700);
        ToCSV("Insertion Sort", 10, 5, 1000);

        // quick sort data
        ToCSV("Quicksort", 20, 7, 1400);
        ToCSV("Quicksort", 25, 9, 1600);
        ToCSV("Quicksort", 30, 12, 1800);
        ToCSV("Quicksort", 35, 14, 2000);
        ToCSV("Quicksort", 40, 16, 2200);
        ToCSV("Quicksort", 15, 7, 1500);

        // mergesort data
        ToCSV("Mergesort", 25, 10, 1600);
        ToCSV("Mergesort", 30, 12, 1800);
        ToCSV("Mergesort", 35, 15, 2000);
        ToCSV("Mergesort", 40, 17, 2200);
        ToCSV("Mergesort", 45, 20, 2400);
        ToCSV("Mergesort", 20, 10, 2000);
    }
}
