package tecnicaltest.reynaldo.seeders;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import tecnicaltest.reynaldo.models.Rol;
import tecnicaltest.reynaldo.models.User;
import tecnicaltest.reynaldo.services.RolService;
import tecnicaltest.reynaldo.services.UserService;

@Component
public class UserSeeder {

    @Autowired
    private RolService rolService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public void run() {
        final Optional<User> oldUser = userService.findByUsuaName("Admin");
        if (!oldUser.isPresent()) {
            final User user = new User();
            user.setUsuaName("Admin");
            user.setUsuaFullName("Admin");
            user.setUsuaPhone("321123456");
            user.setUsuaMail("rvargasgaitan90@gmail.com");
            user.setUsuaDocumentNumber("123456789");
            user.setCreatedBy("Admin");
            user.setUsuaPassword("123456");
            user.setUsuaStatus(true);
            user.setCreatedAt(new Date());
            saveUser(user, "Admin");
        }
    }

    public void saveUser(final User user, final String role) {
        user.setUsuaPassword(bCryptPasswordEncoder.encode(user.getUsuaPassword()));

        final Rol userRole = getRol(role);
        user.setRolId(userRole.getRolId());
        userService.saveUser(user);
    }

    private Rol getRol(final String role) {
        final Optional<Rol> roleExits = rolService.findByRolName(role);

        if (roleExits.isPresent()) {
            return roleExits.get();
        } else {
            final Rol roleToCreate = new Rol();
            roleToCreate.setRolStatus(true);
            roleToCreate.setCreatedBy("Admin");
            roleToCreate.setRolDescripcion(role);
            roleToCreate.setRolName(role);
            roleToCreate.setCreatedAt(new Date());
            rolService.saveRol(roleToCreate);
            return rolService.findByRolName(role).get();
        }

    }
}
