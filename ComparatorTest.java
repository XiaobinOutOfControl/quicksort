import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Assert;

public class ComparatorTest {

    private static class IntWrapper {
        int x;

        IntWrapper(int x) {
            this.x = x;
        }

        public String toString() {
            return "" + x;
        }

        public boolean equals(Object o) {
            if (!(o instanceof IntWrapper)) return false;
            return x == ((IntWrapper) o).x;
        }

        public int hashCode() {
            return Integer.hashCode(x);
        }

        static class Cmp implements Comparator<IntWrapper> {
            public int compare(IntWrapper i1, IntWrapper i2) {
                return i1.x - i2.x;
            }
        }
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerTest() {
        QuickSort.sortArray(null, new IntWrapper.Cmp());
    }

    @Test
    public void edgeCase1() {
        IntWrapper[] wrappers = new IntWrapper[0];
        QuickSort.sortArray(wrappers, new IntWrapper.Cmp());
        String failMessage = "original: [], expected: [], got: %s\n";
        Assert.assertArrayEquals(String.format(failMessage, Arrays.toString(wrappers)),
                            new IntWrapper[0], wrappers);
    }

    @Test
    public void edgeCase2() {
        IntWrapper[] wrappers = new IntWrapper[]{new IntWrapper(1)};
        QuickSort.sortArray(wrappers, new IntWrapper.Cmp());
        String failMessage = "original: [1], expected: [1], got: %s\n";
        Assert.assertArrayEquals(String.format(failMessage, Arrays.toString(wrappers)),
                            new IntWrapper[]{new IntWrapper(1)}, wrappers);
    }

    @Test
    public void extensiveTests() {
        int numTests = 10000;
        int arrayLen = 5;
        IntWrapper[] wrappers = new IntWrapper[arrayLen];
        Random r = new Random();
        Comparator<IntWrapper> cmp = new IntWrapper.Cmp();
        for (int count = 0; count < numTests; ++count) {
            for (int i = 0; i < arrayLen; ++i)
                wrappers[i] = new IntWrapper(r.nextInt(10));
            IntWrapper[] original = wrappers.clone();
            QuickSort.sortArray(wrappers, cmp);
            for (int i = 1; i < arrayLen; ++i) {
                if (cmp.compare(wrappers[i], wrappers[i - 1]) < 0) {
                    String failMessage = "Array not sorted\n" +
                            "Before sorting: " + Arrays.toString(original) +
                            "\nAfter sorting: " + Arrays.toString(wrappers) + "\n";
                    Assert.fail(failMessage);
                }
            }
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ComparatorTest.class);
        result.getFailures().forEach(f -> System.out.println(f));
        if (result.wasSuccessful()) System.out.println("All tests passed");
    }
}