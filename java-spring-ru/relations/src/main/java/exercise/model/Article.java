package exercise.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

// BEGIN
@Getter
@Setter
@Entity
@SuperBuilder
@RequiredArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Lob
    private String body;
    @ManyToOne
    private Category category;
}
// END
