package exercise.dto;

import exercise.model.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@Builder
public class ArticleDto {
    private long id;
    private String name;
    private String body;
    private Category category;
}
// END
