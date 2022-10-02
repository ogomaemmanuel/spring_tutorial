package ke.co.safaricom.blog.api;

import ke.co.safaricom.blog.dto.PostCreateRequest;
import ke.co.safaricom.blog.dto.PostQuery;
import ke.co.safaricom.blog.entities.Post;
import ke.co.safaricom.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "posts")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('Test')")
public class PostController {
    private final PostService postService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<CollectionModel<Post>> index() {
        CollectionModel<Post>  postCollectionModel = CollectionModel.of(postService.getAll());
        postCollectionModel.add(linkTo(methodOn(PostController.class).index()).withRel(IanaLinkRelations.COLLECTION_VALUE));
        return ResponseEntity.ok(postCollectionModel);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest) {

        return ResponseEntity.ok(postService.create(postCreateRequest));
    }

    @GetMapping(value = "/{id}")
   /// @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        return ResponseEntity.of(this.postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
       postService.deleteByID(id);
        return ResponseEntity.badRequest().body("Record not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@Valid @PathVariable("id") Long id, @RequestBody Post postUpdateRequest) {
         Optional<Post> post = postService.updatePost(id, postUpdateRequest);
        return ResponseEntity.of(post);
    }

    @GetMapping("/by-title")

    public ResponseEntity<List<Post>> getAllPostTitles(PostQuery postQuery) {
      var posts=  this.postService.getAllByTitle(postQuery.getTitle());
        return ResponseEntity.ok(posts);
    }


}
