import java.util.Random;
import java.util.Comparator;
import java.util.Arrays;
public class Test {

    private String s;

    public Test(String str) {
        s = str;
    }

    private static class TestComp implements Comparator<Test> {
        public int compare(Test t1, Test t2) {
            return t1.s.compareTo(t2.s);
        }
    }

    public String toString() {
        return s;
    }


    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 6; ++i) {
            Integer[] nums = new Integer[i];
            for (int j = 0; j < i; ++j) {
                nums[j] = r.nextInt(10);
            }
            System.out.println(Arrays.toString(nums));
            QuickSort.sortArray(nums);
            System.out.println(Arrays.toString(nums));
            System.out.println();
        }

        // Test[] t = new Test[]{new Test("c"), new Test("a"), new Test("b")};
        // System.out.println(Arrays.toString(t));
        // QuickSort.sortArray(t, new TestComp());
        // System.out.println(Arrays.toString(t));


        // Integer[] nums = new Integer[]{6,6,6,6,6,6};
        // System.out.println(Arrays.toString(nums));
        // QuickSort.sortArray(nums);
        // System.out.println(Arrays.toString(nums));
    }
}