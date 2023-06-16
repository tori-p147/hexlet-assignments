package exercise.controller;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.service.SearchCriteria;
import exercise.service.UserSpecification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    // BEGIN
    @GetMapping
    public List<User> getUsers(@RequestParam(value = "firstName", required = false) String firstName,
                               @RequestParam(value = "lastName", required = false) String lastName) {
        List<Specification> us = new ArrayList<>();
        if (firstName != null) {
            us.add(new UserSpecification(new SearchCriteria<String>("firstName", firstName)));
        } if (lastName != null) {
            us.add(new UserSpecification(new SearchCriteria<String>("lastName", lastName)));
        }

        Specification<User> i = us.stream()
                .reduce(null, (specResult, spec) -> {
                    if (specResult == null) {
                        return spec;
                    }
                    return specResult.and(spec);
                });
        if (!us.isEmpty()) {
            return this.userRepository.findAll(i);
        }
        return this.userRepository.findAll();
    }
    // END
}

