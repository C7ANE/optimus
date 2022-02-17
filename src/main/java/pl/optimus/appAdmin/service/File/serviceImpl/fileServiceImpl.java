package pl.optimus.appAdmin.service.File.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.optimus.appAdmin.domain.File;
import pl.optimus.appAdmin.repository.FileRepository;
import pl.optimus.appAdmin.service.File.FileService;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class fileServiceImpl implements FileService {

   private final FileRepository fileRepository;

    public fileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(FileDB);
    }

    public File getFile(String id) {
        return fileRepository.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return fileRepository.findAll().stream();
    }

    public String deletFileById(String id) {
        if (fileRepository.existsById(id)) {
            fileRepository.deleteById(id);
            return "File has been successfully deleted";
        }
        return "File doesn't exist";
    }


}
