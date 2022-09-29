package ke.co.safaricom.blog.repositories;

import ke.co.safaricom.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role,Long> {
}
