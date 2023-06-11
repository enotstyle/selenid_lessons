package qap.data;

import qap.domain.User;
import qap.service.SecurityService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class MockUserRepository implements UserRepository {
    @Override
    public Optional<User> findUserByUserName(String userName) {
        final String password = "12345";
        try {
            User user0 = new User("Ivan", 5001, new SecurityService().calculateHash(password));
            User user1 = new User("Stas", 5002, new SecurityService().calculateHash(password));
            User user2 = new User("Artem", 5003, new SecurityService().calculateHash(password));
            user0.addUsersToContactList(user1, user2);
            if(userName.equals("Ivan")) {
                return Optional.of(user0);
            } else {
                return Optional.empty();
            }

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<User> findUserByIcqNumber(String userName) {
        return Optional.empty();
    }
}
