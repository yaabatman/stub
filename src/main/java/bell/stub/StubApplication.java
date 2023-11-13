package bell.stub;

import bell.stub.models.bdConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class StubApplication {

    public static void main(String[] args) {
        SpringApplication.run(StubApplication.class, args);
        bdConnection.getConnection();

    }

}
