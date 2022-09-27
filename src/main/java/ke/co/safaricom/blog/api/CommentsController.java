package ke.co.safaricom.blog.api;

import ke.co.safaricom.blog.dto.CommentCreateRequest;
import ke.co.safaricom.blog.entities.Comment;
import ke.co.safaricom.blog.entities.Post;
import ke.co.safaricom.blog.services.CommentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/comments" )
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComments(@RequestBody @Valid CommentCreateRequest  commentCreateRequest) {
      Comment comment=  this.commentsService.createComment(commentCreateRequest);
      return  ResponseEntity.ok(comment);
    }
}
