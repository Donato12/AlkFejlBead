package hu.elte.MiniNeptun.repositories;

import hu.elte.MiniNeptun.entities.Subject;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
