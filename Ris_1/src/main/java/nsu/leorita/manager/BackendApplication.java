package nsu.leorita.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendApplication {
    public static void main(String[] args) {
        Manager.WORKERS_COUNT = Integer.parseInt(args[0]);
        SpringApplication.run(BackendApplication.class, args);
    }

}