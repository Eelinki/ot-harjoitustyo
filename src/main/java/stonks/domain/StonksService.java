package stonks.domain;

import stonks.dao.UserDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class StonksService {
    private UserDaoImpl userDao;
    private User currentUser;
    
    public StonksService(UserDaoImpl userDao) {
        this.userDao = userDao;
        this.currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public boolean loadUser() {
        User loadedUser = userDao.get();

        if (loadedUser != null) {
            System.out.println("Found a saved user: " + loadedUser.name);

            currentUser = loadedUser;

            return true;
        }

        return false;
    }
    
    public boolean createUser(String name) {
        User loadedUser = userDao.get();
        
        if (loadedUser != null) {
            System.out.println("Found a saved user: " + loadedUser.name);
        } else {
            User newUser = new User(name);
        
            if (userDao.insertUser(newUser)) {
                System.out.println("Saved user successfully");
                
                return true;
            } else {
                System.out.println("Error occurred while saving the user");
            }
        }
        
        return false;
    }

    public void updateUser() {
        if (userDao.insertUser(currentUser)) {
            System.out.println("Saved user successfully");
        } else {
            System.out.println("Error occurred while saving the user");
        }

    }

    public Goal addGoal(String name, String unit, Routine routine, int goal) {
        Goal newGoal = new Goal(name, unit, routine, goal);

        currentUser.goals.add(newGoal);
        updateUser();

        return newGoal;
    }

    public List<Goal> getGoals() {
        return currentUser.goals;
    }
}
