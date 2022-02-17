package pl.optimus.appAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.optimus.appAdmin.domain.File;

@Repository
public interface FileRepository extends JpaRepository<File,String> {
}
