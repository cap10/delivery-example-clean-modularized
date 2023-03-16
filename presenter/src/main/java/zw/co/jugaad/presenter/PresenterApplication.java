package zw.co.jugaad.presenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication(scanBasePackages = "zw.co.jugaad")
@EnableJpaRepositories(basePackages = {"zw.co.jugaad.data.repositories"})
@EntityScan(basePackages = {"zw.co.jugaad.data.entities"})
@ComponentScan(basePackages = "zw.co.jugaad")
public class PresenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresenterApplication.class, args);
    }

}
