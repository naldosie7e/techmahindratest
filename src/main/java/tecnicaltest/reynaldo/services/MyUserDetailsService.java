package tecnicaltest.reynaldo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tecnicaltest.reynaldo.models.MyUserDetails;
import tecnicaltest.reynaldo.repositories.UserRepository;
import tecnicaltest.reynaldo.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsuaName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + userName));
        return user.map(MyUserDetails::new).get();
    }
}
