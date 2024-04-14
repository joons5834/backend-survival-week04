package kr.megaptera.assignment.repositories;

import kr.megaptera.assignment.exceptions.PostNotFound;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepository {
    private final Map<PostId, Post> posts;

    public PostRepository() {
        posts = new HashMap<>();
    }

    public List<Post> findAll() {
        return posts.values().stream().toList();
    }

    public Post find(PostId id) {
        Post post = posts.get(id);
        if (post == null) {
            throw new PostNotFound();
        }
        return post;
    }

    public void save(Post post) {
        posts.put(post.id(), post);
    }

    public void delete(PostId id) {
        posts.remove(id);
    }

}
