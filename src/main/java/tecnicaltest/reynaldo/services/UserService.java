package tecnicaltest.reynaldo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tecnicaltest.reynaldo.models.User;
import tecnicaltest.reynaldo.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<User> usersList() {
        return (List<User>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return (Optional<User>) repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsuaName(String usuaName) {
        return (Optional<User>) repository.findByUsuaName(usuaName);
    }

    @Transactional(readOnly = true)
    public Page<User> findAllPageable(String search, Pageable pageable) {

        if (search.equals("null")) {
            return repository.findAll(pageable);
        }
        return repository.findByUsuaNameOrUsuaMailOrUsuaFullNameOrUsuaPhoneContainingIgnoreCase(search, search, search,
                search, pageable);
    }

    @Transactional
    public User saveUser(User user) {
        User objectSaved = repository.saveAndFlush(user);
        return objectSaved;

    }

    public String getUserName(User user) {
        String[] mail_base = user.getUsuaMail().split("\\@");
        String possibleUser = mail_base[0];
        if (possibleUser.length() > 40) {
            String newUser = possibleUser.substring(0, 40);
            possibleUser = newUser;
        }

        Optional<User> validateUser = repository.findByUsuaName(possibleUser);
        Integer contador = 1;
        while (validateUser.isPresent()) {
            possibleUser = possibleUser + contador;
            validateUser = repository.findByUsuaName(possibleUser);
        }
        return possibleUser;
    }

    @Transactional
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
