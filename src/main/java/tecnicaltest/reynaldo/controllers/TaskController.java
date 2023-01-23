package tecnicaltest.reynaldo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;

import tecnicaltest.reynaldo.models.Task;
import tecnicaltest.reynaldo.services.TaskService;
import tecnicaltest.reynaldo.util.JwtUtil;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Return all the task list whitout any filter")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(taskService.taksList());
    }

    @Operation(summary = "Return all the task list filter by title and description")
    @RequestMapping(value = "paginate-list-by-title-and-description/{search}/{page}/{limit}", method = RequestMethod.GET)
    public Page<Task> paginateListByTitleAndDescription(@PathVariable("search") final String search,
            @PathVariable("page") final Integer page, @PathVariable("limit") final Integer limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "createdAt"));
        return taskService.findAllPageableByTitleAndDescription(search, pageable);
    }

    @Operation(summary = "Return all the task list filter by an user and the beging and end date")
    @RequestMapping(value = "paginate-list-by-user-and-range-date/{page}/{limit}", method = RequestMethod.GET)
    public Page<Task> paginateByUserAndRangeDate(@RequestBody final Task filter,
            @PathVariable("page") final Integer page, @PathVariable("limit") final Integer limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "createdAt"));
        return taskService.findAllPageableUserAndBeginAndDate(filter, pageable);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid final Task ntask,
            @RequestHeader(name = "Authorization") final String authorization) throws Exception {

        try {
            ntask.setCreatedAt(new Date());
            ntask.setCreatedBy(jwtUtil.extractUserName(authorization.substring(7)));
            return ResponseEntity.ok(taskService.saveTask(ntask));
        } catch (DataAccessException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody @Valid final Task idUpdate,
            @RequestHeader(name = "Authorization") final String authorization) throws Exception {
        try {
            final Optional<Task> model = taskService.findById(id);
            if (model.isPresent()) {
                final Task upTask = model.get();
                upTask.setLastModifiedAt(new Date());
                upTask.setLastModifiedBy(jwtUtil.extractUserName(authorization.substring(7)));
                return ResponseEntity.ok(taskService.saveTask(upTask));// update in Task
            }
            return ResponseEntity.notFound().build();
        } catch (final DataAccessException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Task>> find(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Task> o = taskService.findById(id);
        if (o.isPresent()) {
            taskService.delete(o.get().getTasId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
