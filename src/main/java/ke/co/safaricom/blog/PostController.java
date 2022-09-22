package ke.co.safaricom.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "posts")
public class PostController {

    private final PostRepository postRepository;
@Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @GetMapping
    @ResponseBody
    public List<Post> index() {
        return postRepository.findAll();
    }

    @PostMapping
    public Post createPost(@Valid @RequestBody Post post ) {
        return postRepository.save(post);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable("id") Long id) {
       return this.postRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        var postDB=this.postRepository.findById(id).get();
        postDB.setBody(post.getBody());
        postDB.setAuthor(post.getAuthor());
        postDB.setTitle(post.getTitle());
        postRepository.save(postDB);
        return postDB;
    }
}
