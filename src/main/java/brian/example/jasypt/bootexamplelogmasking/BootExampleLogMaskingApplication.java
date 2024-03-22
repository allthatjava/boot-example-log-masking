package brian.example.jasypt.bootexamplelogmasking;

import brian.example.jasypt.bootexamplelogmasking.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BootExampleLogMaskingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BootExampleLogMaskingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Person p = new Person("Brian", "Heo", "1234567812345678", "123 ABC Street");

        log.info("Person log:{}",p);
    }
}
