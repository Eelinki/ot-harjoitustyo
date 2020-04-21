package stonks.domain;

import stonks.dao.UserDaoImpl;

import java.util.ArrayList;

public class StonksService {
    private UserDaoImpl userDao;
    private User currentUser;
    private ArrayList<Goal> goals;
    
    public StonksService(UserDaoImpl userDao) {
        this.userDao = userDao;
        this.currentUser = null;
        this.goals = new ArrayList<>();
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
            User testi = new User(name);
        
            if (userDao.insertUser(testi)) {
                System.out.println("Saved user successfully");
                
                return true;
            } else {
                System.out.println("Error occurred while saving the user");
            }
        }
        
        return false;
    }

    public Goal addGoal(String name, String unit, Routine routine, int goal) {
        Goal newGoal = new Goal(name, unit, routine, goal);

        goals.add(newGoal);

        return newGoal;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }
}
