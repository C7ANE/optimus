package pl.optimus.appAdmin.service.File;

import org.springframework.web.multipart.MultipartFile;
import pl.optimus.appAdmin.domain.File;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

public interface FileService {

    public File store(MultipartFile file) throws IOException;
    public File getFile(String id);
    public Stream<File> getAllFiles();
    public String deletFileById(String id);
}
