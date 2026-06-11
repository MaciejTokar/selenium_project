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
//        jenkins - poczytać
//        dopisac dodatkowy jakis test
//        przerzucic wszystkie wait z basepage do oddzielnego utils
//        przypomniec exceptiony i waity, rodzielenie, dziedziczenie
//        dodać tagi w ramach testow - np. @smoke/@regression/@admin itp - 1 test moze miec kilka tagow @ingegration @acceptance @system
//        uruchamianie w maven tagu, wszystkich oprocz 1, 2 na raz  mvn clean test -Dtest=CucumberRunnerTest "-Dcucumber.filter.tags=@smoke"
    }
}