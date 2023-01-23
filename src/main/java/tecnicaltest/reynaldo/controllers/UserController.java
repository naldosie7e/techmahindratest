package tecnicaltest.reynaldo.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import tecnicaltest.reynaldo.models.User;
import tecnicaltest.reynaldo.services.UserService;
import tecnicaltest.reynaldo.util.JwtUtil;
import tecnicaltest.reynaldo.util.SetAleatoryPassword;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private SetAleatoryPassword setAleatoryPassword;

    @Operation(summary = "Return all the users list whitout any filter")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(userService.usersList());
    }

    @Operation(summary = "Return all the usersfilter by the user name, the mail or full name")
    @RequestMapping(value = "paginate-list/{search}/{page}/{limit}", method = RequestMethod.GET)
    public Page<User> paginateList(@PathVariable("search") final String search,
            @PathVariable("page") final Integer page, @PathVariable("limit") final Integer limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC, "createdAt"));
        return userService.findAllPageable(search, pageable);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid final User nuser,
            @RequestHeader(name = "Authorization") final String authorization) throws Exception {

        try {
            nuser.setCreatedAt(new Date());
            nuser.setUsuaName(userService.getUserName(nuser));
            nuser.setCreatedBy(jwtUtil.extractUserName(authorization.substring(7)));
            nuser.setUsuaPassword(setAleatoryPassword.setAleatoryPassword());
            return ResponseEntity.ok(userService.saveUser(nuser));
        } catch (DataAccessException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody @Valid final User idUpdate,
            @RequestHeader(name = "Authorization") final String authorization) throws Exception {
        try {
            final Optional<User> model = userService.findById(id);
            if (model.isPresent()) {
                final User upUsser = model.get();
                upUsser.setUsuaFullName(idUpdate.getUsuaFullName());
                upUsser.setUsuaMail(idUpdate.getUsuaMail());
                upUsser.setUsuaPhone(idUpdate.getUsuaPhone());
                upUsser.setRolId(idUpdate.getRolId());
                upUsser.setUsuaStatus(idUpdate.isUsuaStatus());
                upUsser.setUsuaDocumentNumber(idUpdate.getUsuaDocumentNumber());
                upUsser.setLastModifiedAt(new Date());
                upUsser.setLastModifiedBy(jwtUtil.extractUserName(authorization.substring(7)));
                return ResponseEntity.ok(userService.saveUser(upUsser));// update in user
            }
            return ResponseEntity.notFound().build();
        } catch (final DataAccessException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<User>> find(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<User> o = userService.findById(id);
        if (o.isPresent()) {
            userService.deleteUser(o.get().getUsuaId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
