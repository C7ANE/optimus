package pl.optimus.appAdmin.service.UploadFile;

import org.springframework.web.multipart.MultipartFile;
import pl.optimus.appAdmin.domain.UplodedFile;

public interface UploadFileService {

    void uploadToLocal(MultipartFile file);

    UplodedFile uploadToDb(MultipartFile file);

    UplodedFile downloadFile(String fileId);
}
