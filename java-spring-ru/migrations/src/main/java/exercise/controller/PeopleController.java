package exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {


    private final JdbcTemplate jdbc;


    @PostMapping
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping
    public Map<String, Object> getPeople() {
        String query = "SELECT * FROM person";
        return jdbc.query(query, (ResultSetExtractor<Map<String, Object>>) rs -> {
            HashMap<String, Object> mapRet = new HashMap<>();
            while (rs.next()) {
                mapRet.put(rs.getString("first_name"), rs.getString("last_name"));
            }
            return mapRet;
        });
    }

    @GetMapping(value = "/{id}")
    public Map<String, Object> getPerson(@PathVariable int id) {
        String query = "SELECT first_name, last_name FROM person where id =(" + id + ")";
        return jdbc.queryForMap(query);
    }

    // END
}
