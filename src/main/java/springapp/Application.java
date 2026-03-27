package springapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        DRY -  Dont repeat yourself
//        KISS - keep it simple stupid
//        TODO
//        wyszukac webelement dla warning popupu i zrobic analogiczna metode jak do success popupu
    }
}