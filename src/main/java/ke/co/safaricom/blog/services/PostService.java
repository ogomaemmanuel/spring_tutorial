package ke.co.safaricom.blog.services;

import ke.co.safaricom.blog.dto.PostCreateRequest;
import ke.co.safaricom.blog.repositories.PostRepository;
import ke.co.safaricom.blog.entities.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(PostCreateRequest postCreateRequest) {
       var post= Post.postfromDto(postCreateRequest);
        return postRepository.save(post);
    }
    public Optional<Post> updatePost (Long postId,Post post) {
     var postToUpdate=   this.postRepository.findById(postId);
     postToUpdate.ifPresent(p->{
         p.setBody(post.getBody());
         p.setAuthor(post.getAuthor());
         p.setTitle(post.getTitle());
         postRepository.save(p);
     });
     return postToUpdate;
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> getAllByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public void deleteByID(Long id) {
        postRepository.deleteById(id);
    }
}
