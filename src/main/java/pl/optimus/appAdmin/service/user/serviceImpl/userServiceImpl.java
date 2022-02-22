package pl.optimus.appAdmin.service.user.serviceImpl;

import org.springframework.stereotype.Service;
import pl.optimus.appAdmin.domain.User;
import pl.optimus.appAdmin.service.user.UserService;

import javax.servlet.http.HttpServletRequest;

@Service
public class userServiceImpl implements UserService {
     public void saveUserSerialNumber(User userModel, HttpServletRequest request) {
        String[] userSerialNumbers = request.getParameterValues("userSerialNumber");
        for (String userSerialNumber : userSerialNumbers) {
            userModel.addDetails(userSerialNumber);
        }
    }
}
