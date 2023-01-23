package tecnicaltest.reynaldo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tecnicaltest.reynaldo.models.User;
import tecnicaltest.reynaldo.repositories.UserRepository;
import tecnicaltest.reynaldo.viewmodels.AuthenticationRequest;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Transactional(readOnly = true)
    public User findByUsuaName(AuthenticationRequest authenticationRequest) {

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final User user = repository.findByUsuaName(userDetails.getUsername()).get();
        user.setUsuaPassword("");
        return user;
    }

}
