package tecnicaltest.reynaldo.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import tecnicaltest.reynaldo.services.AuthService;
import tecnicaltest.reynaldo.services.MyUserDetailsService;
import tecnicaltest.reynaldo.util.JwtUtil;
import tecnicaltest.reynaldo.viewmodels.AuthenticationRequest;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenicationManager;

    @Mock
    private MyUserDetailsService userDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthService authService;

    @InjectMocks
    AuthController authController;

    @Test
    void testAuthenticate() throws Exception {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder().password("123456")
                .username("Admin").build();

        ResponseEntity<?> testResult = authController.authenticate(authenticationRequest);
        Assertions.assertEquals(HttpStatus.OK, testResult.getStatusCode());

    }
}
