package exercise.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.GenerationType;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String title;

    @Lob
    private String body;

    private PostState state = PostState.CREATED;

    // BEGIN
    public boolean publish() {
        if (state == PostState.CREATED) {
            this.state = PostState.PUBLISHED;
            return true;
        } else {
            return false;
        }
    }

    public boolean archive() {
        if (state == PostState.PUBLISHED || state == PostState.CREATED) {
            this.state = PostState.ARCHIVED;
            return true;
        } else {
            return false;
        }
    }
    // END
}
