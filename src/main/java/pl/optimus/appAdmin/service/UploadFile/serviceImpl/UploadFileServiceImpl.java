package pl.optimus.appAdmin.service.UploadFile.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.optimus.appAdmin.domain.UplodedFile;
import pl.optimus.appAdmin.repository.UploadFileRepository;
import pl.optimus.appAdmin.response.UploadFileResponse;
import pl.optimus.appAdmin.service.UploadFile.UploadFileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private String uploadFolderPath = "\\Users\\Adrian\\Desktop\\uploaded_";

    private UploadFileRepository uploadFileRepo;

    @Override
    public void uploadToLocal(MultipartFile file){
        try{
            byte[] data = file.getBytes();
            Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
            Files.write(path,data);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @Override
    public UplodedFile uploadToDb(MultipartFile file){

        UplodedFile uploadFile = new UplodedFile();
        UploadFileResponse response = new UploadFileResponse();

        try{
            uploadFile.setFileData(file.getBytes());
            uploadFile.setFileType(file.getContentType());
            uploadFile.setFileName(file.getOriginalFilename());
            UplodedFile uploadFileToRet = uploadFileRepo.save(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        return uploadFile;
    }
    @Override
    public UplodedFile downloadFile(String fileId){
        UplodedFile uplodedFileToRet = uploadFileRepo.getById(fileId);
        return uplodedFileToRet;

    }

}
