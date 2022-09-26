package ke.co.safaricom.blog.repositories;

import ke.co.safaricom.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment,Long> {
}
