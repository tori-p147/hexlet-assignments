package exercise.controller;

import exercise.model.Person;
import exercise.dto.PersonDto;
import exercise.repository.PersonRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    // Автоматически заполняем значение поля
    private final PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping
    public Person createPerson(@RequestBody PersonDto dto) {
        Person person = new Person();
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        return this.personRepository.save(person);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable Long id) {
        this.personRepository.deleteById(id);
    }

    @PatchMapping(value = "/{id}")
    public void updatePerson(@PathVariable Long id, @RequestBody PersonDto dto) {
        Person updated = personRepository.findById(id).orElseThrow();
        updated.setFirstName(dto.getFirstName());
        updated.setLastName(dto.getLastName());
        this.personRepository.save(updated);
    }
    // END
}
