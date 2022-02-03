package pl.optimus.appAdmin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.optimus.appAdmin.domain.Register;

@Repository
public interface RegisterRepository extends CrudRepository<Register, Long> {
}
