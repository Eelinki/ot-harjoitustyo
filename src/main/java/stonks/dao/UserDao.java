package stonks.dao;

import stonks.domain.User;

public interface UserDao {
    User get();
    boolean insertUser(User user);
}
