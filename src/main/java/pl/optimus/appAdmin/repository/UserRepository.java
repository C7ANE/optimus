package pl.optimus.appAdmin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.optimus.appAdmin.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
