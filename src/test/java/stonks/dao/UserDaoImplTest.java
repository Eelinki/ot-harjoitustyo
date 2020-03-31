package stonks.dao;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.FileWriter;
import stonks.dao.UserDaoImpl;
import stonks.domain.User;

public class UserDaoImplTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    UserDaoImpl userDao;
    File userFile;
    
    @Before
    public void setUp() throws IOException {
        userFile = folder.newFile("testuser.json");
        
        try (FileWriter fileWriter = new FileWriter(userFile.getAbsolutePath())) {
            fileWriter.write("{\"name\":\"testuser\"}");
        }
        
        userDao = new UserDaoImpl(userFile.getAbsolutePath());
    }
    
    @Test
    public void userCanBeReadFromFile() {
        User user = userDao.get();
        assertEquals("testuser", user.name);
    }
}
