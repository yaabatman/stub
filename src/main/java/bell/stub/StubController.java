package bell.stub;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
public class StubController {

    @GetMapping("/stub_get")
    public ResponseEntity<User> EndpointGet() {
        User user = new User("admin", "admin123");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/stub_post")
    public ResponseEntity<User> EndpointPost(@RequestBody User user) {
        if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
        LocalDate date = LocalDate.now();
        user.setDate(date);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
