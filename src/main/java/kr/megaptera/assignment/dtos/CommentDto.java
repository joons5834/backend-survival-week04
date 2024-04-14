package kr.megaptera.assignment.dtos;

import kr.megaptera.assignment.models.Comment;

public class CommentDto {
    private String id;
    private String author;
    private String content;

    public CommentDto(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public CommentDto(Comment comment) {
        this(comment.id().toString(), comment.author(), comment.content());
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
