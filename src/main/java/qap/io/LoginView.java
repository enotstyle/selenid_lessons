package qap.io;

import qap.domain.User;
import qap.exeption.AuthenticateException;

public interface LoginView extends View {


    User doLogin() throws AuthenticateException;
}
