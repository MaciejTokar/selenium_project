package springapp;

import org.slf4j.Logger;
import io.cucumber.java.After;
import org.slf4j.LoggerFactory;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class CucumberHooks {

    private final static Logger logger = LoggerFactory.getLogger(CucumberHooks.class);

    @Before
    public void initialization(final Scenario scenario) {
        logger.info("Test scenario is running: " + logger.getName());
    }

    @After
    public void afterScenario(final Scenario scenario) {
        logger.info("Test scenario is ending: " + logger.getName());
    }
}
