import java.util.Comparator;
import java.util.Random;

public class QuickSort {
    private static Random r = new Random();

    public static <T> void sortArray(T[] array) {
        sortArray(array, null);
    }

    public static <T> void sortArray(T[] array, Comparator<T> c) {
        quickSort(array, 0, array.length - 1, c);
    }

    @SuppressWarnings("unchecked")
    private static <T> void quickSort(T[] array, int lb, int ub, Comparator<T> c) {
        if (lb >= ub) return;
        int pivotIdx = r.nextInt(ub + 1 - lb) + lb;
        T pivot = array[pivotIdx];
        swap(array, pivotIdx, ub);
        int left = lb, right = ub;
        while (left <= right) {
            int leftDiff = c == null? 
                            ((Comparable<T>) array[left]).compareTo(pivot)
                                : c.compare(array[left], pivot);
            if (leftDiff < 0) {
                ++left;
                continue;
            }
            if (left > right) break;
            int rightDiff = c == null?
                        ((Comparable<T>) array[right]).compareTo(pivot)
                            : c.compare(array[right], pivot);
            if (rightDiff >= 0) {
                --right;
                continue;
            }
            swap(array, left++, right--);
        }
        swap(array, left, ub);
        quickSort(array, lb, left - 1, c);
        while (++left < ub) {
            int neighborDiff = c == null?
                            ((Comparable<T>) array[left - 1]).compareTo(array[left])
                                : c.compare(array[left - 1], array[left]);
            if (neighborDiff != 0) break;
        }
        quickSort(array, left, ub, c);
    }

    private static <T> void swap(T[] array, int i1, int i2) {
        T tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }
}
