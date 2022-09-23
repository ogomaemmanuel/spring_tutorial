package ke.co.safaricom.blog.api;

import ke.co.safaricom.blog.dao.PostCreateRequest;
import ke.co.safaricom.blog.entities.Post;
import ke.co.safaricom.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Post>> index() {

        return ResponseEntity.ok(postService.getAll());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest) {

        return ResponseEntity.ok(postService.create(postCreateRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        return ResponseEntity.of(this.postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
       postService.deleteByID(id);
        return ResponseEntity.badRequest().body("Record not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post postUpdateRequest) {
         Optional<Post> post = postService.updatePost(id, postUpdateRequest);
        return ResponseEntity.of(post);
    }


}
