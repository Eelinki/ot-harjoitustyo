package stonks.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import stonks.domain.User;

public class UserDaoImpl implements UserDao {
    ObjectMapper mapper = new ObjectMapper();
    private String file;
    
    public UserDaoImpl(String file) {
        this.file = file;
    }
    
    public UserDaoImpl() {
        this.file = "user.json";
    }
    
    @Override
    public User get() {
        try {
            User user = mapper.readValue(new File(file), User.class);
            return user;
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }

    @Override
    public boolean insertUser(User user) {
        try {
            mapper.writeValue(new File(file), user);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
