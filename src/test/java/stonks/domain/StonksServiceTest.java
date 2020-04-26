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

        userDao = new UserDaoImpl(userFile.getAbsolutePath());

        stonksService = new StonksService(userDao);
    }

    @Test
    public void currentUserReturnsNullWhenNoUserHasLoggedIn() {
        assertNull(stonksService.getCurrentUser());
    }

    @Test
    public void currentUserReturnsUserWhenUserHasLoggedIn() throws IOException {
        try (FileWriter fileWriter = new FileWriter(userFile.getAbsolutePath())) {
            fileWriter.write("{\"name\":\"testuser\"}");
        }

        stonksService.loadUser();
        assertTrue(stonksService.getCurrentUser() instanceof User);
    }

    @Test
    public void loadUserReturnsFalseWhenNoUsersExist() throws IOException {
        try (FileWriter fileWriter = new FileWriter(userFile.getAbsolutePath())) {
            fileWriter.write("{\"name\":\"testuser\"}");
        }

        try {
            userFile = folder.newFile("testuser.json");
            userFile.delete();

            assertFalse(stonksService.loadUser());
        } catch (IOException e) {

        }
    }

    @Test
    public void createUserReturnsTrueWhenNoUsersExist() {
        assertTrue(stonksService.createUser("newuser123"));
    }

    @Test
    public void getGoalsReturnsCorrectAmountOfGoals() {
        stonksService.createUser("testuser");
        stonksService.loadUser();
        assertEquals(0, stonksService.getGoals().size());
        stonksService.addGoal("drink beer", "cans", Routine.DAILY, 6);
        assertEquals(1, stonksService.getGoals().size());
    }
}