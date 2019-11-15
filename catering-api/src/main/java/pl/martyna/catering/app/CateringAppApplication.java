package pl.martyna.catering.app;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class CateringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CateringAppApplication.class, args);
    }

}
