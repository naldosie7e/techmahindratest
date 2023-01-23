package tecnicaltest.reynaldo.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tecnicaltest.reynaldo.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByTasTitleOrTasDecriptionContainingIgnoreCase(
            String tasTitle, String tasDecription, Pageable pageable);

    Page<Task> findAllByTasAssignToAndTasBeginDateAfterAndTasEndDateBefore(Long tasAssignTo, Date tasBeginDate,
            Date tasEndDate, Pageable pageable);

}