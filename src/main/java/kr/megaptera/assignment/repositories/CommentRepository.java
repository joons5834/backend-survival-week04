package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentRepository {

    private final Map<CommentId, Comment> comments;

    public CommentRepository() {
        comments = new HashMap<>();
    }

    public List<Comment> findAll(PostId postId) {
        return comments.values()
                .stream().filter(comment -> comment.postId().equals(postId))
                .toList();
    }

    public void save(Comment created) {
        comments.put(created.id(), created);
    }

    public Comment find(CommentId commentId, PostId postId) {
        Comment found = comments.get(commentId);
        if (found == null || !found.postId().equals(postId)) {
            throw new PostNotFound();
        }

        return found;
    }

    public void delete(CommentId commentId) {
        comments.remove(commentId);
    }
}
