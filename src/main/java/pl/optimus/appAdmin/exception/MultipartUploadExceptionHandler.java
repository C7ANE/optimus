package pl.optimus.appAdmin.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class MultipartUploadExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handlerUploadFileException(MaxUploadSizeExceededException exception, HttpServletRequest request, HttpServletResponse response){
        return "File size limit exceeded.Please make sure the fill size is well with in 128mb";
    }
}
