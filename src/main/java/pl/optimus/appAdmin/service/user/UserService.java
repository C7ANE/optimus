package pl.optimus.appAdmin.service.user;

import pl.optimus.appAdmin.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
     void saveUserSerialNumber(User userModel, HttpServletRequest request);

}

