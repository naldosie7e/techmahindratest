package tecnicaltest.reynaldo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import tecnicaltest.reynaldo.models.User;
import tecnicaltest.reynaldo.services.AuthService;
import tecnicaltest.reynaldo.services.MyUserDetailsService;
import tecnicaltest.reynaldo.util.JwtUtil;
import tecnicaltest.reynaldo.viewmodels.AuthenticationRequest;
import tecnicaltest.reynaldo.viewmodels.AuthenticationResponse;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenicationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Operation(summary = "Validate the credetentials and return the user and the token")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenicationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final User user = authService.findByUsuaName(authenticationRequest);
        final String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken, user));
    }

}