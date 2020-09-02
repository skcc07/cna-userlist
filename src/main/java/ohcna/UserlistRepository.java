package ohcna;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserlistRepository extends CrudRepository<Userlist, Long> {

    List<Userlist> findByUserId(String userId);

        void deleteByUserId(String userId);
}