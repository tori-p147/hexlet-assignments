package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    int[] ints;

    int result;

    public MinThread(int[] ints) {
        this.ints = ints;
    }

    @Override
    public void run() {
        result = Arrays.stream(ints).min().getAsInt();
        interrupt();
    }

    int getResult() {
        return result;
    }
}
// END
