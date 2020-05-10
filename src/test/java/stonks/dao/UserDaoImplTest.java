package stonks.dao;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import stonks.domain.User;

public class UserDaoImplTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    UserDao userDao;
    File userFile;
    
    @Before
    public void setUp() throws IOException {
        userFile = folder.newFile("testuser.json");
        
        userDao = new UserDaoImpl(userFile.getAbsolutePath());
    }
    
    @Test
    public void userCanBeReadFromFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(userFile.getAbsolutePath())) {
            fileWriter.write("{\"name\":\"test123\",\"goals\":[]}");
        }

        User user = userDao.get();
        assertEquals("test123", user.name);
    }

    @Test
    public void insertUserWritesFileCorrectly() throws IOException {
        User user = new User("test123", new ArrayList<>());
        userDao.insertUser(user);

        String jsonFile = Files.readString(userFile.toPath(), StandardCharsets.UTF_8);

        assertEquals("{\"name\":\"test123\",\"goals\":[]}", jsonFile);
    }

    @Test
    public void defaultFileNameIsUsedWhenNoFileNameIsSupplied() throws IOException {
        userDao = new UserDaoImpl();
        User user = new User("test123", new ArrayList<>());
        userDao.insertUser(user);

        String jsonFile = Files.readString(Path.of("user.json"), StandardCharsets.UTF_8);

        assertEquals("{\"name\":\"test123\",\"goals\":[]}", jsonFile);
    }

    @Test
    public void getReturnsNullWhenUserFileDoesNotExist() {
        assertNull(userDao.get());
    }
}
