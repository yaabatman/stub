package bell.stub;

import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;


@RestController
@RequestMapping
public class StubController {
    User returnUser = new User("admin", "admin123");

    @GetMapping("/stub_get")
    public User EndpointGet() {
        return returnUser;
    }

    @PostMapping("/stub_post")
    public User EndpointPost(@RequestBody User user) {
        Date date = Calendar.getInstance().getTime();
        user.setDate(date);
        return user;
    }
}
