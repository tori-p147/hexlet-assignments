package exercise.controller;

import exercise.dto.ArticleDto;
import exercise.model.Article;
import exercise.model.Category;
import exercise.repository.ArticleRepository;
import exercise.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleRepository.deleteById(id);
    }

    // BEGIN
    @PostMapping
    public void createArticle(@RequestBody ArticleDto dto) {
        Category category = categoryRepository.findById(dto.getCategory().getId());
        Article save = Article.builder()
                .body(dto.getBody())
                .name(dto.getName())
                .category(category).build();

        articleRepository.save(save);
    }

    @PatchMapping(path = "/{id}")
    public void updateArticle(@RequestBody ArticleDto updateArticle, @PathVariable Long id) {
        Category category = categoryRepository.findById(updateArticle.getCategory().getId());

        Article updated = Article.builder()
                .category(category)
                .id(id)
                .body(updateArticle.getBody())
                .name(updateArticle.getName()).build();
        articleRepository.save(updated);
    }

    @GetMapping(path = "/{id}")
    public Article getArticle(@PathVariable long id) {
        return articleRepository.findById(id);
    }
    // END
}
