package qap.data;

import qap.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByUserName(String userName);
    Optional<User> findUserByIcqNumber(String userName);
}
