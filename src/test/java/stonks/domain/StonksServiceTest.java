package stonks.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import stonks.dao.UserDaoImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class StonksServiceTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    UserDaoImpl userDao;
    File userFile;
    StonksService stonksService;

    @Before
    public void setUp() throws IOException {
        userFile = folder.newFile("testuser.json");

        try (FileWriter fileWriter = new FileWriter(userFile.getAbsolutePath())) {
            fileWriter.write("{\"name\":\"testuser\"}");
        }

        userDao = new UserDaoImpl(userFile.getAbsolutePath());

        stonksService = new StonksService(userDao);
    }

    @Test
    public void currentUserReturnsNullWhenNoUserHasLoggedIn() {
        assertNull(stonksService.getCurrentUser());
    }

    @Test
    public void currentUserReturnsUserWhenUserHasLoggedIn() {
        stonksService.loadUser();
        assertTrue(stonksService.getCurrentUser() instanceof User);
    }

    @Test
    public void loadUserReturnsFalseWhenNoUsersExist() throws IOException {
        try {
            userFile = folder.newFile("testuser.json");
            userFile.delete();

            assertFalse(stonksService.loadUser());
        } catch (IOException e) {

        }
    }
}