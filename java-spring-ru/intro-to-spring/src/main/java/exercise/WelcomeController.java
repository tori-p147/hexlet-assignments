package exercise;

import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {
    @GetMapping(value = "/")
    public String welcome() {
        return "Welcome to Spring";
    }

    @GetMapping(value = "/hello")
    public String hello(@Nullable @RequestParam String name) {
        return "Hello, ".concat(name != null ? name : "World");
    }
}
// END
