package exercise;

import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    static MaxThread threadMaxSearch;
    static MinThread threadMinSearch;

    public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};
        System.out.println(App.getMinMax(numbers));
    }

    public static Map<String, Integer> getMinMax(int[] arrayInt) {
        threadMaxSearch = new MaxThread(arrayInt);
        threadMinSearch = new MinThread(arrayInt);
        threadMaxSearch.start();
        LOGGER.info("Thread " + threadMaxSearch.getName() + " started");
        threadMinSearch.start();
        LOGGER.info("Thread " + threadMinSearch.getName() + " started");
        int max = 0;
        int min = 0;
        if (threadMaxSearch.isInterrupted() && threadMinSearch.isInterrupted()) {
            LOGGER.info("Thread " + threadMaxSearch.getName() + " finished");
            LOGGER.info("Thread " + threadMinSearch.getName() + " finished");
            max = threadMaxSearch.getResult();
            min = threadMinSearch.getResult();
        }
        return Map.of("min", min, "max", max);
    }
    // END
}
