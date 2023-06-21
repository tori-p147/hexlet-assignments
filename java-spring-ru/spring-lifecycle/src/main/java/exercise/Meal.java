package exercise;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

// BEGIN
@Component(value = "meal")
// END
public class Meal implements BeanPostProcessor {
    public String getMealForDaytime(String daytime) {

        switch (daytime) {
            case "morning":
                return "breakfast";
            case "day":
                return "lunch";
            case "evening":
                return "dinner";
            default:
                return "nothing :)";
        }
    }

    // Для самостоятельной работы
    // BEGIN

    // END
}
