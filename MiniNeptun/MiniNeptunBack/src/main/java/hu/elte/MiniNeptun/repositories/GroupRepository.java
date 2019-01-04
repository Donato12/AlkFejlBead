package hu.elte.MiniNeptun.repositories;

import hu.elte.MiniNeptun.entities.Group;
import hu.elte.MiniNeptun.entities.Subject;
import hu.elte.MiniNeptun.entities.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    List<Group> findAllBySubject(Subject s); 
}
