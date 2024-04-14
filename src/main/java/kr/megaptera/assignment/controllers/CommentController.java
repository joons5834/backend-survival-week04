package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController() {
        commentService = new CommentService();
    }

    @GetMapping
    public List<CommentDto> getComments(@RequestParam("postId") String postId) {
        List<CommentDto> commentDtos = commentService.getCommentDtos(postId);
        return commentDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(@RequestParam("postId") PostId postId,
                             @RequestBody CommentDto commentDto) {
        CommentDto created = commentService.createComment(postId, commentDto);
        return created;
    }

    @PatchMapping("/{id}")
    public CommentDto update(@PathVariable("id") CommentId commentId,
                             @RequestParam("postId") PostId postId,
                             @RequestBody CommentDto commentDto) {
        Comment found = commentService.getCommentDto(commentId, postId);
        found.update(commentDto);
        return new CommentDto(found);
    }

    @DeleteMapping("/{id}")
    public CommentDto delete(@PathVariable("id") CommentId commentId,
                             @RequestParam("postId") PostId postId) {
        Comment found = commentService.deleteComment(commentId, postId);
        return new CommentDto(found);
    }


    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }

}
