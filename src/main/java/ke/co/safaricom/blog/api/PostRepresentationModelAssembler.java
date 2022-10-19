package ke.co.safaricom.blog.api;

import ke.co.safaricom.blog.dto.PostCreateRequest;
import ke.co.safaricom.blog.entities.Post;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.QueryParameter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.mediatype.Affordances;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
public class PostRepresentationModelAssembler implements RepresentationModelAssembler<Post, EntityModel<Post>> {
    @Override
    public EntityModel<Post> toModel(Post entity) {
        Link findOneLink = linkTo(methodOn(PostController.class).getPost(entity.getId())).withSelfRel().withName("Get Post").withTitle("Get Post By ID");
       var link= Affordances.of(findOneLink).afford(HttpMethod.PUT).withOutput(Post.class).withInput(PostCreateRequest.class).withName("Update Posts")
               .andAfford(HttpMethod.DELETE).withName("Delete Posts").addParameters(//
                QueryParameter.optional("name"), //
                QueryParameter.optional("role"))
               .toLink();
        return EntityModel.of(entity,link);
    }


}
