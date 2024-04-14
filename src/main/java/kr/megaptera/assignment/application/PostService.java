package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository postRepository;

    public PostService() {
        postRepository = new PostRepository();
    }

    public List<PostDto> getPostDtos() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> new PostDto(post)).toList();
    }

    public PostDto getPostDto(String id) {
        Post found = postRepository.find(PostId.of(id));
        return new PostDto(found);
    }

    public PostDto createPost(PostDto postDto) {
        Post created = new Post(postDto.getTitle(), postDto.getAuthor(),
                postDto.getContent());
        postRepository.save(created);

        return new PostDto(created);
    }

    public PostDto updatePost(String id, PostDto postDto) {
        Post found = postRepository.find(PostId.of(id));
        found.update(postDto.getTitle(), postDto.getContent());
        return new PostDto(found);
    }

    public PostDto deletePost(String id) {
        Post found = postRepository.find(PostId.of(id));
        postRepository.delete(PostId.of(id));
        return new PostDto(found);
    }
}
