import java.util.Random;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Assert;

public class ComparableTest {

    @Test(expected = NullPointerException.class)
    public void nullPointerTest() {
        QuickSort.sortArray(null);
    }

    @Test
    public void edgeCase1() {
        Integer[] nums = new Integer[0];
        QuickSort.sortArray(nums);
        String failMessage = "original: [], expected: [], got: %s\n";
        Assert.assertArrayEquals(String.format(failMessage, Arrays.toString(nums)),
                            new Integer[0], nums);
    }

    @Test
    public void edgeCase2() {
        Integer[] nums = new Integer[]{1};
        QuickSort.sortArray(nums);
        String failMessage = "original: [1], expected: [1], got: %s\n";
        Assert.assertArrayEquals(String.format(failMessage, Arrays.toString(nums)),
                            new Integer[]{1}, nums);
    }

    @Test
    public void extensiveTests() {
        int numTests = 10000;
        int arrayLen = 5;
        Integer[] nums = new Integer[arrayLen];
        Random r = new Random();
        for (int count = 0; count < numTests; ++count) {
            for (int i = 0; i < arrayLen; ++i) nums[i] = r.nextInt(10);
            Integer[] original = nums.clone();
            QuickSort.sortArray(nums);
            for (int i = 1; i < arrayLen; ++i) {
                if (nums[i] < nums[i - 1]) {
                    String failMessage = "Array not sorted\n" +
                            "Before sorting: " + Arrays.toString(original) +
                            "\nAfter sorting: " + Arrays.toString(nums) + "\n";
                    Assert.fail(failMessage);
                }
            }
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ComparableTest.class);
        result.getFailures().forEach(f -> System.out.println(f));
        if (result.wasSuccessful()) System.out.println("All tests passed");
    }
}