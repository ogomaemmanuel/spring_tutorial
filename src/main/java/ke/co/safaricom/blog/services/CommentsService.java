package ke.co.safaricom.blog.services;

import ke.co.safaricom.blog.dto.CommentCreateRequest;
import ke.co.safaricom.blog.dto.CommentsFilter;
import ke.co.safaricom.blog.entities.Comment;
import ke.co.safaricom.blog.entities.Post;
import ke.co.safaricom.blog.repositories.CommentsRepository;
import ke.co.safaricom.blog.repositories.PostRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
private final PostRepository postRepository;
    public CommentsService(CommentsRepository commentsRepository, PostRepository postRepository) {
        this.commentsRepository = commentsRepository;
        this.postRepository = postRepository;
    }

    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        Post post = this.postRepository.getReferenceById(commentCreateRequest.getPostId());
        Comment comment = new Comment();
        comment.setBody(commentCreateRequest.getBody());
        comment.setPost(post);
        this.commentsRepository.save(comment);
        return comment;
    }

    public Page<Comment> getComments(Pageable pageable, CommentsFilter filter) {
        return this.commentsRepository.findAll(filter.commentExample(),pageable);
    } public Page<Comment> getComments(Pageable pageable) {
        return this.commentsRepository.findAll(pageable);
    }

    public Optional<Comment> getCommentById(Long id) {
       return this.commentsRepository.findById(id);
    }
}
