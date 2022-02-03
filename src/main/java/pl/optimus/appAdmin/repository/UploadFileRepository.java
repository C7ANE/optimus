package pl.optimus.appAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.optimus.appAdmin.domain.UplodedFile;

@Repository
public interface UploadFileRepository extends JpaRepository<UplodedFile,String> {

}
