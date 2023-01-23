package tecnicaltest.reynaldo.controllers;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tecnicaltest.reynaldo.models.User;
import tecnicaltest.reynaldo.services.UserService;
import tecnicaltest.reynaldo.util.JwtUtil;
import tecnicaltest.reynaldo.util.SetAleatoryPassword;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserService userService;

    @Mock
    private SetAleatoryPassword setAleatoryPassword;

    @InjectMocks
    UserController userController;

    @Test
    void testCreate() throws Exception {
        User user = new User();
        user.setCreatedAt(new Date());
        user.setUsuaPassword("test");
        user.setUsuaFullName("test");
        user.setUsuaMail("test@gmail.com");
        user.setUsuaPhone("3212184711");
        user.setRolId(Long.parseLong("1"));
        user.setUsuaStatus(true);

        ResponseEntity<?> testResult = userController.create(user, "tokentest");
        Assertions.assertEquals(HttpStatus.OK, testResult.getStatusCode());
    }

    @Test
    void testDelete() {
        ResponseEntity<?> testResult = userController.delete(Long.parseLong("1"));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, testResult.getStatusCode());
    }

    @Test
    void testFind() {
        ResponseEntity<?> testResult = userController.find(Long.parseLong("1"));
        Assertions.assertEquals(HttpStatus.OK, testResult.getStatusCode());
    }

    @Test
    void testIndex() {
        ResponseEntity<?> testResult = userController.index();
        Assertions.assertEquals(HttpStatus.OK, testResult.getStatusCode());
    }

    @Test
    void testPaginateList() {

        Page<User> testResult = userController.paginateList("test", 0, 10);
        Assertions.assertEquals(null, testResult);
    }

    @Test
    void testUpdate() throws NumberFormatException, Exception {
        User user = new User();
        user.setCreatedAt(new Date());
        user.setUsuaPassword("test");
        user.setUsuaFullName("test");
        user.setUsuaMail("test@gmail.com");
        user.setUsuaPhone("3212184711");
        user.setRolId(Long.parseLong("1"));
        user.setUsuaStatus(true);

        ResponseEntity<?> testResult = userController.update(Long.parseLong("1"),
                user, "tokentest");
        Assertions.assertEquals(HttpStatus.NOT_FOUND, testResult.getStatusCode());
    }
}
