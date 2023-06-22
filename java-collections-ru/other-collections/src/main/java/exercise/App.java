package exercise;

import java.util.*;

// BEGIN
public class App {
    public static HashMap<String, Object> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        HashMap<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> stringObjectEntry : map1.entrySet()) {
            if (map2.containsKey(stringObjectEntry.getKey())) {
                if (stringObjectEntry.getValue().equals(map2.get(stringObjectEntry.getKey()))) {
                    result.put(stringObjectEntry.getKey(), "unchanged");
                } else {
                    result.put(stringObjectEntry.getKey(), "changed");
                }
            } else {
                result.put(stringObjectEntry.getKey(), "deleted");
            }
        }
        for (Map.Entry<String, Object> stringObjectEntry : map2.entrySet()) {
            if (!map1.containsKey(stringObjectEntry.getKey())) {
                result.put(stringObjectEntry.getKey(), "added");
            }
        }
        return result;
    }
}
//END
