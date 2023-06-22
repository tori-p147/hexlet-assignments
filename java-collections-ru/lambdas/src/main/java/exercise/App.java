package exercise;

import java.util.Arrays;

// BEGIN
class App {
    public static String[] duplicateValues(String[] items) {

        return Arrays.stream(items)
                .flatMap(item -> Arrays.stream(new String[] {item, item}))
                .toArray(String[]::new);
    }

    public static String[][] enlargeArrayImage(String[][] image) {

        String[][] horizontalyStretched = Arrays.stream(image)
                .map(App::duplicateValues)
                .toArray(String[][]::new);

        return Arrays.stream(horizontalyStretched)
                .flatMap(item -> Arrays.stream(new String[][] {item, item}))
                .toArray(String[][]::new);
    }
}
// END
