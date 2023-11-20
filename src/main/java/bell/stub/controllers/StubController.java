package bell.stub.controllers;

import bell.stub.database.BdConnection;
import bell.stub.files.ReadFromFile;
import bell.stub.files.WriteToFile;
import bell.stub.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;


@RestController
public class StubController {

    @GetMapping("/stub_get")
    public ResponseEntity<?> EndpointGet(@RequestParam(value = "login") String login) {

        User user = BdConnection.selectUserByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(new SQLException(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        WriteToFile.writeToChosenFile(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/stub_post")
    public ResponseEntity<?> EndpointPost(@RequestBody User user) {
        if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(new SQLException(), HttpStatus.BAD_REQUEST);
        }
        user.setDate(Date.valueOf(LocalDate.now()));
        int rows = BdConnection.insertUser(user);
        return new ResponseEntity<>(rows, HttpStatus.OK);
    }

    @GetMapping("/stub_read_line")
    public ResponseEntity<?> EndpointGetRandomLine() {

        String randomReadLine = ReadFromFile.readFromFile();
        if (randomReadLine == null) {
            return new ResponseEntity<>(new SQLException(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(randomReadLine, HttpStatus.OK);
    }
}
