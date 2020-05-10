package stonks.dao;

import stonks.domain.User;

public interface UserDao {
    /**
     * Loads the user from an existing .json file.
     * @return true if user loaded successfully, otherwise return false
     */
    User get();

    /**
     * Method for saving the user into a .json file.
     *
     * @param user User to be saved
     * @return true if the user was saved successfully, otherwise returns false
     */
    boolean insertUser(User user);
}
