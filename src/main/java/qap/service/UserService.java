package qap.service;

import qap.domain.User;
import qap.exeption.AuthenticateException;

public interface UserService {

    User authenticate(String userName, String password) throws AuthenticateException;

}
