package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans2(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.get("gender") == "male")
                .sorted((user1, user2) -> {
                    LocalDate date1 = LocalDate.parse(user1.get("birthday"));
                    LocalDate date2 = LocalDate.parse(user2.get("birthday"));
                    return date1.compareTo(date2);
                })
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
    }

    // alternative solution
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(map1 -> map1.get("gender").equals("male"))
                .sorted(Comparator.comparingLong(map2 -> LocalDate.parse(map2.get("birthday")).toEpochDay()))
                .map(map3 -> map3.get("name"))
                .collect(Collectors.toList());
    }

}
// END
