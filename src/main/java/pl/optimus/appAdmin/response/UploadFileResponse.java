package pl.optimus.appAdmin.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponse {

    private String fileId;

    private String fileType;

    private String message;

    private boolean uploadStatus;

    private String downloadUri;

}
