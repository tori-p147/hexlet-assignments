package exercise;

import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        System.out.println(App.getMinMax(numbers));
    }

    public static Map<String, Integer> getMinMax(int[] arrayInt) {
        MaxThread threadMaxSearch = new MaxThread(arrayInt);
        MinThread threadMinSearch = new MinThread(arrayInt);
        threadMaxSearch.start();
        LOGGER.info("Thread " + threadMaxSearch.getName() + " started");
        try {
            threadMaxSearch.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Thread " + threadMaxSearch.getName() + " finished");
        threadMinSearch.start();
        LOGGER.info("Thread " + threadMinSearch.getName() + " started");
        try {
            threadMinSearch.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Thread " + threadMinSearch.getName() + " finished");
        int max = threadMaxSearch.getResult();
        int min = threadMinSearch.getResult();
        return Map.of("min", min, "max", max);
    }
    // END
}
