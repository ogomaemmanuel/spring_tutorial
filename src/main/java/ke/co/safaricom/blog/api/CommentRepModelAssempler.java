package ke.co.safaricom.blog.api;

import ke.co.safaricom.blog.entities.Comment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class CommentRepModelAssempler implements RepresentationModelAssembler<Comment, EntityModel<Comment>> {
    @Override
    public EntityModel<Comment> toModel(Comment entity) {
        EntityModel model = EntityModel.of(entity);
        model.add(linkTo(methodOn(CommentsController.class).getCommentById(entity.getId())).withSelfRel());
        model.add(linkTo(methodOn(PostController.class).getPost(entity.getPost().getId())).withRel("post"));

        return model;
    }
}
