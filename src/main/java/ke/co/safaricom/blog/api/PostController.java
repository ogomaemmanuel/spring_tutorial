package ke.co.safaricom.blog.api;

import ke.co.safaricom.blog.dto.PostCreateRequest;
import ke.co.safaricom.blog.dto.PostQuery;
import ke.co.safaricom.blog.entities.Post;
import ke.co.safaricom.blog.services.PostService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "posts")

//@PreAuthorize("hasAuthority('Test')")
public class PostController {
    private final PostService postService;
    private final PostRepresentationModelAssembler modelAssembler;

    public PostController(PostService postService, PostRepresentationModelAssembler modelAssembler) {
        this.postService = postService;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping(produces = {
        MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE
    })
    @ResponseBody
    public ResponseEntity<CollectionModel<EntityModel<Post>>> index() {
        var postCollectionModel = this.modelAssembler.toCollectionModel(postService.getAll());
        postCollectionModel.add(linkTo(methodOn(PostController.class).index()).withRel(IanaLinkRelations.COLLECTION_VALUE));
        return ResponseEntity.ok(postCollectionModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Post>> createPost(@Valid @RequestBody PostCreateRequest postCreateRequest) {
        var post = postService.create(postCreateRequest);
        var model = modelAssembler.toModel(post);
        return ResponseEntity.created(linkTo(methodOn(PostController.class).getPost(post.getId())).toUri()).body(model);
    }

    @GetMapping(value = "/{id}",produces = {
            MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE
    })
    /// @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EntityModel<Post>> getPost(@PathVariable("id") Long id) {
        var post = this.postService.getPostById(id);
      return post.map(modelAssembler::toModel).map(ResponseEntity::ok).orElse( ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        postService.deleteByID(id);
        return ResponseEntity.badRequest().body("Record not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Post>> updatePost(@PathVariable("id") Long id, @Valid @RequestBody Post postUpdateRequest) {
        Optional<Post> post = postService.updatePost(id, postUpdateRequest);
        return post.map(modelAssembler::toModel).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-title")
    public ResponseEntity<CollectionModel<EntityModel<Post>>> getAllPostTitles(PostQuery postQuery) {
        var posts = this.postService.getAllByTitle(postQuery.getTitle());
       var collectionModel= modelAssembler.toCollectionModel(posts);
        return ResponseEntity.ok(collectionModel);
    }


}
