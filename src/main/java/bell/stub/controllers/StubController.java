package bell.stub.controllers;

import bell.stub.models.User;
import bell.stub.models.bdConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class StubController {

    @GetMapping("/stub_get")
    public ResponseEntity<?> EndpointGet() {
        String login = "user1";
        Connection connection = bdConnection.getConnection();
        User user = bdConnection.selectUserByLogin(connection, login);
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
        int rows = bdConnection.insertUser(bdConnection.getConnection(), user);
        return new ResponseEntity<>(rows, HttpStatus.OK);
    }
}
