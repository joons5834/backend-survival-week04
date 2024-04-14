package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.PostService;
import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.exceptions.PostNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController() {
        postService = new PostService();
    }

    @GetMapping
    public List<PostDto> getPosts() {
        List<PostDto> postDtos = postService.getPostDtos();
        return postDtos;
    }

    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {
        PostDto postDto = postService.getPostDto(id);
        return postDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostDto postDto) {
        PostDto created = postService.createPost(postDto);
        return created;
    }

    @PatchMapping("/{id}")
    public PostDto update(@PathVariable String id,
                          @RequestBody PostDto postDto) {
        PostDto updated = postService.updatePost(id, postDto);
        return updated;
    }

    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable("id") String id) {
        PostDto postDto = postService.deletePost(id);
        return postDto;
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다";
    }
}
