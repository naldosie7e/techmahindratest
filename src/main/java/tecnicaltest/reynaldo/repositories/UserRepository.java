package tecnicaltest.reynaldo.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tecnicaltest.reynaldo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsuaName(String userName);

    Optional<User> findByUsuaMail(String usuaMail);

    Page<User> findByUsuaNameOrUsuaMailOrUsuaFullNameOrUsuaPhoneContainingIgnoreCase(
            String usuaName, String usuaMail, String usuaFullName, String usuaPhone, Pageable pageable);
}