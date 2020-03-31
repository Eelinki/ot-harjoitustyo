package stonks;

import java.util.ArrayList;
import stonks.domain.User;
import stonks.dao.UserDaoImpl;

public class Main {
    public static void main(String args[]) {
        UserDaoImpl userDao = new UserDaoImpl();
        
        User loadedUser = userDao.get();
        
        if(loadedUser != null) {
            System.out.println("Found a saved user: " + loadedUser.name);
        } else {
            User testi = new User("testuser");
        
            if(userDao.insertUser(testi)) {
                System.out.println("Saved user successfully");
            } else {
                System.out.println("Error occurred while saving the user");
            }
        }
    }
}
