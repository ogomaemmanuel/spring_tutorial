package ke.co.safaricom.blog;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Post>> index() {

        return ResponseEntity.ok(postRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        return ResponseEntity.ok(postRepository.save(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        return ResponseEntity.of(this.postRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        var post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.deleteById(id);
           return ResponseEntity.ok("record deleted successfully");
        };

        return ResponseEntity.badRequest().body("Record not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        var postDB = this.postRepository.findById(id).get();
        postDB.setBody(post.getBody());
        postDB.setAuthor(post.getAuthor());
        postDB.setTitle(post.getTitle());
        postRepository.save(postDB);
        return ResponseEntity.ok(postDB);
    }


}
