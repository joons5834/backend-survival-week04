package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;

import java.util.List;

public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService() {
        this.commentRepository = new CommentRepository();
    }

    public List<CommentDto> getCommentDtos(String postId) {
        List<Comment> comments = commentRepository.findAll(PostId.of(postId));
        return comments.stream().map(comment -> new CommentDto(comment)).toList();
    }

    public Comment getCommentDto(CommentId commentId, PostId postId) {
        Comment comment = commentRepository.find(commentId, postId);
        return comment;
    }

    public CommentDto createComment(PostId postId, CommentDto commentDto) {
        Comment created = new Comment(commentDto, postId);
        commentRepository.save(created);
        return new CommentDto(created);
    }

    public Comment deleteComment(CommentId commentId, PostId postId) {
        Comment found = commentRepository.find(commentId, postId);
        commentRepository.delete(commentId);
        return found;
    }
}
