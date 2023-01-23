package tecnicaltest.reynaldo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tecnicaltest.reynaldo.models.Task;
import tecnicaltest.reynaldo.repositories.TaskRepository;

@Service
public class TaskService {
    @Autowired
    TaskRepository repository;

    @Transactional
    public Task saveTask(Task task) {
        Task object = repository.saveAndFlush(task);
        return object;
    }

    @Transactional(readOnly = true)
    public List<Task> taksList() {
        return (List<Task>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Task> findAllPageableByTitleAndDescription(String search, Pageable pageable) {

        if (search.equals("null")) {
            return repository.findAll(pageable);
        }
        return repository.findByTasTitleOrTasDecriptionContainingIgnoreCase(search, search, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Task> findAllPageableUserAndBeginAndDate(Task filter, Pageable pageable) {

        return repository.findAllByTasAssignToAndTasBeginDateAfterAndTasEndDateBefore(filter.getTasAssignTo(),
                filter.getTasBeginDate(), filter.getTasEndDate(), pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Task> findById(Long id) {
        return (Optional<Task>) repository.findById(id);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
