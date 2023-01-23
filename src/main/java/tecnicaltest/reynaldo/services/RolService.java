package tecnicaltest.reynaldo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tecnicaltest.reynaldo.models.Rol;
import tecnicaltest.reynaldo.repositories.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository repository;

    @Transactional(readOnly = true)
    public Optional<Rol> findByRolName(String rolName) {
        return (Optional<Rol>) repository.findByRolName(rolName);
    }

    @Transactional
    public Rol saveRol(Rol rol) {
        Rol objectSaved = repository.saveAndFlush(rol);
        return objectSaved;

    }
}
