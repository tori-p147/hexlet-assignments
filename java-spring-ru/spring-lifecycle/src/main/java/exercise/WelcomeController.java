package exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    private Meal meal;

    @GetMapping("/daytime")
    public String getMeal() {
        String mealForDaytime = meal.getMealForDaytime(String.valueOf(LocalDateTime.now().getHour()));
        if (mealForDaytime.equals("morning")) {
            return "It is morning now. Enjoy your breakfast";
        }
        if (mealForDaytime.equals("day")) {
            return "It is day now. Enjoy your lunch";
        }
        if (mealForDaytime.equals("evening")) {
            return "It is morning now. Enjoy your dinner";
        }
        return "evening";
    }
}
// END
