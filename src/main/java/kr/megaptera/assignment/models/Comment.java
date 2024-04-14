package kr.megaptera.assignment.models;

import kr.megaptera.assignment.dtos.CommentDto;

public class Comment {
    private CommentId id;
    private String author;
    private String content;
    private PostId postId;

    public Comment(CommentId id, String author, String content, PostId postId) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.postId = postId;
    }

    public Comment(CommentDto commentDto, PostId postId) {
        this(CommentId.generate(), commentDto.getAuthor(),
                commentDto.getContent(), postId);
    }

    public CommentId id() {
        return id;
    }

    public String author() {
        return author;
    }

    public String content() {
        return content;
    }

    public PostId postId() {
        return postId;
    }

    public void update(CommentDto commentDto) {
        this.content = commentDto.getContent();
    }
}
