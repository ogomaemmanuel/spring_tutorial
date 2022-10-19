package ke.co.safaricom.blog.api;

import ke.co.safaricom.blog.dto.CommentCreateRequest;
import ke.co.safaricom.blog.dto.CommentsFilter;
import ke.co.safaricom.blog.entities.Comment;
import ke.co.safaricom.blog.services.CommentsService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/comments" )
public class CommentsController {
private final PasswordEncoder  passwordEncoder;
private final CommentRepModelAssempler commentRepModelAssempler;

private  final PagedResourcesAssembler<Comment> pagedResourcesAssembler;
    private final CommentsService commentsService;

    public CommentsController(PasswordEncoder passwordEncoder, CommentRepModelAssempler commentRepModelAssempler, PagedResourcesAssembler<Comment> pagedResourcesAssembler, CommentsService commentsService) {
        this.passwordEncoder = passwordEncoder;
        this.commentRepModelAssempler = commentRepModelAssempler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;

        this.commentsService = commentsService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<EntityModel<Comment>> createComments(@RequestBody @Valid CommentCreateRequest  commentCreateRequest) {
      Comment comment=  this.commentsService.createComment(commentCreateRequest);
     var entityModel=  commentRepModelAssempler.toModel(comment);
      return  ResponseEntity.ok(entityModel);
    }

    @GetMapping
    public ResponseEntity<?> getComments(Pageable pageable, CommentsFilter filter) {
       var comments= this.commentsService.getComments(pageable,filter);

     var pagedResources=  pagedResourcesAssembler.toModel(comments,commentRepModelAssempler);
       return ResponseEntity.ok(pagedResources);
    }

    @GetMapping(value = "/{id}")

    public ResponseEntity<EntityModel<Comment>> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment= this.commentsService.getCommentById(id);
        return comment.map(commentRepModelAssempler::toModel).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
