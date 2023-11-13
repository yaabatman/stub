package bell.stub.controllers;

import bell.stub.models.User;
import bell.stub.models.bdConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;


@RestController
public class StubController {

    @GetMapping("/stub_get")
    public ResponseEntity<?> EndpointGet() {
        String login = "user1";
        User user = bdConnection.selectUserByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(new SQLException(), HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/stub_post")
    public ResponseEntity<?> EndpointPost(@RequestBody User user) {
        if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(new SQLException(), HttpStatusCode.valueOf(400));
        }
        String email = user.getLogin() + "@mail.com";
        user.setDate(Date.valueOf(LocalDate.now()));
        user.setEmail(email);
        int rows = bdConnection.insertUser(user);
        return new ResponseEntity<>(rows, HttpStatus.OK);
    }
}
