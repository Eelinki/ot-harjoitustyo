package stonks.domain;

import stonks.dao.UserDao;

import java.util.List;

/**
 * Contains the functionality of the app
 */
public class StonksService {
    private UserDao userDao;
    private User currentUser;

    public StonksService(UserDao userDao) {
        this.userDao = userDao;
        this.currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Loads the user from an existing .json file.
     *
     * @return true if user loaded successfully, otherwise return false
     */
    public boolean loadUser() {
        User loadedUser = userDao.get();

        if (loadedUser != null) {
            System.out.println("Found a saved user: " + loadedUser.name);

            currentUser = loadedUser;

            return true;
        }

        return false;
    }

    /**
     * Creates a new user and saves it to a .json file.
     *
     * @param name Name of the user
     * @return true if the user was created successfully, otherwise returns false
     */
    public boolean createUser(String name) {
        User loadedUser = userDao.get();
        
        if (loadedUser != null) {
            System.out.println("Found a saved user: " + loadedUser.name);

            return false;
        }

        User newUser = new User(name);

        if (userDao.insertUser(newUser)) {
            System.out.println("Saved user successfully");

            return true;
        } else {
            System.out.println("Error occurred while saving the user");
        }

        return false;
    }

    /**
     * Updates the users .json file.
     */
    public void updateUser() {
        if (userDao.insertUser(currentUser)) {
            System.out.println("Saved user successfully");
        } else {
            System.out.println("Error occurred while saving the user");
        }

    }

    /**
     * Adds a goal to the users goals list and updates the users .json file
     *
     * @param newGoal goal to be added
     *
     * @see StonksService#updateUser()
     *
     * @return the added goal
     */
    public Goal addGoal(Goal newGoal) {
        currentUser.goals.add(newGoal);
        updateUser();

        return newGoal;
    }

    public List<Goal> getGoals() {
        return currentUser.goals;
    }
}
