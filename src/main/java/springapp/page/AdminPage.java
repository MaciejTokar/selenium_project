package springapp.page;

import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class AdminPage {

    private final WebDriverWait wait;

    @FindBy(css = ".oxd-button--secondary:nth-of-type(1)")
    private WebElement addButton;

    @FindBy(css = ".oxd-grid-item--gutters:nth-of-type(1) .oxd-select-wrapper")
    private WebElement userRoleDropDown;

    @FindBy(css = ".oxd-grid-item--gutters:nth-of-type(3) .oxd-select-wrapper")
    private WebElement statusDropDown;

    @FindBy(xpath = "//span[text()='ESS']")
    private WebElement essOption;

    //    @FindBy(xpath = "//span[text()='Enabled']")
    @FindBy(css = ".oxd-select-option:nth-of-type(2)")
    private WebElement enabledOption;

    @FindBy(css = ".oxd-userdropdown-name")
    private WebElement userName;

    @FindBy(css = ".oxd-autocomplete-text-input input")
    private WebElement employeeNameInput;

    @FindBy(css = ".oxd-autocomplete-option:nth-of-type(1)")
    private WebElement employeeNameOption;

    @FindBy(css = ".oxd-grid-item--gutters:nth-of-type(4) input")
    private WebElement usernameInput;

    @FindBy(css = ".user-password-cell input")
    private WebElement passwordInput;

    @FindBy(css = ".user-password-row .oxd-grid-item--gutters:nth-of-type(2) input")
    private WebElement passwordConfirmInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(css = ".oxd-toast--success")
    private WebElement successPopUp;

    public AdminPage() {
        initElements(getDriver(), this);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(7));
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void clickUserRoleDropDown() {
        userRoleDropDown.click();
    }

    public void clickEssOption() {
        essOption.click();
    }

    public void clickStatusDropDown() {
        statusDropDown.click();
    }

    public void clickEnabledOption() {
        if (enabledOption.getText().equals("Enabled")) {
            enabledOption.click();
        }
    }

    public void enterEmployeeNameInput() {
        employeeNameInput.sendKeys(userName.getText());
    }

    public void clickEmployeeNameOption() {
//        wait.until(ExpectedConditions.visibilityOf(employeeNameOption));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".oxd-autocomplete-option")));
//        Thread.sleep(10000);
        try {
            wait.until(d -> false);
        } catch (TimeoutException ignored) {
            employeeNameOption.click();
        }
    }

    public void enterUsernameInput(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public void enterPasswordConfirmInput(String password) {
        passwordConfirmInput.sendKeys(password);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void assertionSuccessPopUpDisplay() {
        wait.until(ExpectedConditions.visibilityOf(successPopUp));
        Assertions.assertTrue(successPopUp.isDisplayed(), "After creating user account success confirmation is displayed");
    }
}
