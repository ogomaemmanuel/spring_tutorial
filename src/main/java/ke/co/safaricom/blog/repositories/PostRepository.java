package ke.co.safaricom.blog.repositories;

import ke.co.safaricom.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(value = "Select p from Post p where p.title=:title")
    List<Post> findByTitle(String title);
//
//    @Query(value = "Select * from posts where posts.body=:body", nativeQuery = true)
//    List<Post> findByBody(String body);
//    List<Post> findByAuthor(String author);
//    List<Post> findByAuthorOrBody(String author);
}
