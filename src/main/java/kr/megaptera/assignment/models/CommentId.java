package kr.megaptera.assignment.models;

import com.github.f4b6a3.tsid.TsidCreator;

import java.util.Objects;

public class CommentId {
    private String id;

    public CommentId(String id) {
        this.id = id;
    }

    public static CommentId of(String id) {
        return new CommentId(id);
    }

    public static CommentId generate() {
        return new CommentId(TsidCreator.getTsid().toString());
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return Objects.equals(id, commentId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
