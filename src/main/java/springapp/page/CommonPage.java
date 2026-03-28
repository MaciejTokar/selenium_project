package springapp.page;

import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;
import static springapp.driverSingleton.ConfigHelper.getTimeoutDuration;

public class CommonPage extends BasePage {
    protected final Logger LOGGER = LogManager.getLogger(this);
    private final WebDriverWait wait;

    @FindBy(css = ".oxd-autocomplete-text-input input")
    protected WebElement employeeNameInput;

    @FindBy(css = ".oxd-autocomplete-option:nth-of-type(1)")
    protected WebElement employeeNameOption;

    @FindBy(xpath = "//form/div[1]/div/div/div[@class='oxd-input-group oxd-input-field-bottom-space']/span")
    private WebElement invalidEmployeeNameLabel;

    @FindBy(css = ".oxd-userdropdown-name")
    private WebElement userName;

    @FindBy(css = ".oxd-toast--success")
    private WebElement successPopUp;

    public CommonPage() {
        initElements(getDriver(), this);
        wait = new WebDriverWait(getDriver(), getTimeoutDuration());
    }

    public CommonPage enterEmployeeNameInput(String name) {
        waitForVisibility(employeeNameInput);
        if (name.equalsIgnoreCase("valid")) {
            typeText(employeeNameInput, userName.getText());
        } else if (name.equalsIgnoreCase("invalid")) {
            typeText(employeeNameInput, "/.,[]");
        }
        return this;
    }

    public CommonPage clickEmployeeNameOption(String name) {
        if (name.equalsIgnoreCase("invalid")) {
            runAfterTimeout(() -> clickButton(employeeNameOption));
            wait.until(ExpectedConditions.visibilityOf(invalidEmployeeNameLabel));
            assertTrue(invalidEmployeeNameLabel.isDisplayed(), "Validation of employee name hasn't been displayed");
        } else if (name.equalsIgnoreCase("valid")) {
            runAfterTimeout(() -> clickButton(employeeNameOption));
        }

        return this;
    }

    public CommonPage assertionSuccessPopUpDisplay() {
        wait.until(ExpectedConditions.visibilityOf(successPopUp));
        assertTrue(successPopUp.isDisplayed(), "After creating user account success confirmation is displayed");
        return this;
    }
}
