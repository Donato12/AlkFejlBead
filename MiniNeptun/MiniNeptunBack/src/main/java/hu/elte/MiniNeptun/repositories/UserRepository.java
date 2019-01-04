package hu.elte.MiniNeptun.repositories;

import hu.elte.MiniNeptun.entities.Group;
import hu.elte.MiniNeptun.entities.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
