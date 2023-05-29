package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    int[] ints;

    int result;

    public MaxThread(int[] ints) {
        this.ints = ints;
    }

    @Override
    public void run() {
        result = Arrays.stream(ints).max().getAsInt();
        interrupt();
    }

    int getResult() {
        return result;
    }
}
// END
