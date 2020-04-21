package stonks.dao;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
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
        
        userDao = new UserDaoImpl(userFile.getAbsolutePath());
    }
    
    @Test
    public void userCanBeReadFromFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(userFile.getAbsolutePath())) {
            fileWriter.write("{\"name\":\"testuser\"}");
        }

        User user = userDao.get();
        assertEquals("testuser", user.name);
    }

    @Test
    public void insertUserWritesFileCorrectly() throws IOException {
        User user = new User("test123");
        userDao.insertUser(user);

        String jsonFile = Files.readString(userFile.toPath(), StandardCharsets.UTF_8);

        assertEquals("{\"name\":\"test123\"}", jsonFile);
    }

    @Test
    public void getReturnsNullWhenUserFileDoesNotExist() {
        assertNull(userDao.get());
    }
}
