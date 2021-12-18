package pl.optimus.appAdmin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.optimus.appAdmin.domain.Candidate;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {
}
