package exercise.controller;

import exercise.ResourceNotFoundException;
import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping(value = "/{postId}/comments")
    public Iterable<Comment> getAllCommentsByPostId(@PathVariable Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(value = "/{postId}/comments/{commentId}")
    public Comment getCommentByIdAndPostId(@PathVariable Long postId,
                                           @PathVariable Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    @PostMapping(value = "/{postId}/comments")
    public void createComment(@RequestBody CommentDto dto, @PathVariable Long postId) {
        Comment c = new Comment();
        c.setContent(dto.content());
        c.setPost(postRepository.findById(postId).orElseThrow());
        commentRepository.save(c);
    }

    @PatchMapping(value = "/{postId}/comments/{commentId}")
    public void updateComment(@RequestBody CommentDto dto, @PathVariable Long postId, @PathVariable Long commentId) {
        Comment c = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        c.setContent(dto.content());
        commentRepository.save(c);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        Comment c = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(c);
    }
    // END
}
