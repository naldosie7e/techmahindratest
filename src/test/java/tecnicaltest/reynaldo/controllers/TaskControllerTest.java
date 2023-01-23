package tecnicaltest.reynaldo.controllers;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tecnicaltest.reynaldo.models.Task;
import tecnicaltest.reynaldo.services.TaskService;
import tecnicaltest.reynaldo.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private TaskService taskService;

    @InjectMocks
    TaskController taskController;

    @Test
    void testCreate() throws Exception {
        Task newTaskTest = new Task();
        newTaskTest.setTasAssignTo(Long.parseLong("1"));
        newTaskTest.setTasBeginDate(new Date());
        newTaskTest.setTasEndDate(new Date());
        newTaskTest.setTasTitle("test");
        newTaskTest.setTasDecription("test");
        newTaskTest.setTasHoursExpect(100);
        newTaskTest.setTasStatus(true);
        ResponseEntity<?> testResult = taskController.create(newTaskTest, "tokentest");
        Assertions.assertEquals(HttpStatus.OK, testResult.getStatusCode());
    }

    @Test
    void testDelete() {
        ResponseEntity<?> testResult = taskController.delete(Long.parseLong("1"));
        Assertions.assertEquals(HttpStatus.NOT_FOUND, testResult.getStatusCode());
    }

    @Test
    void testFind() {
        ResponseEntity<?> testResult = taskController.find(Long.parseLong("1"));
        Assertions.assertEquals(HttpStatus.OK, testResult.getStatusCode());
    }

    @Test
    void testIndex() {
        ResponseEntity<?> testResult = taskController.index();
        Assertions.assertEquals(HttpStatus.OK, testResult.getStatusCode());
    }

    @Test
    void testPaginateByUserAndRangeDate() {
        Task filterTest = new Task();
        filterTest.setTasAssignTo(Long.parseLong("1"));
        filterTest.setTasBeginDate(new Date());
        filterTest.setTasEndDate(new Date());

        Page<Task> testResult = taskController.paginateByUserAndRangeDate(filterTest, 0, 10);
        Assertions.assertEquals(null, testResult);
    }

    @Test
    void testPaginateListByTitleAndDescription() {

        Page<Task> testResult = taskController.paginateListByTitleAndDescription("test", 0, 10);
        Assertions.assertEquals(null, testResult);
    }

    @Test
    void testUpdate() throws Exception {
        Task updateTaskTest = new Task();
        updateTaskTest.setTasAssignTo(Long.parseLong("1"));
        updateTaskTest.setTasBeginDate(new Date());
        updateTaskTest.setTasEndDate(new Date());
        updateTaskTest.setTasTitle("test");
        updateTaskTest.setTasDecription("test");
        updateTaskTest.setTasHoursExpect(100);
        updateTaskTest.setTasStatus(true);
        ResponseEntity<?> testResult = taskController.update(Long.parseLong("1"), updateTaskTest, "tokentest");
        Assertions.assertEquals(HttpStatus.NOT_FOUND, testResult.getStatusCode());
    }
}
