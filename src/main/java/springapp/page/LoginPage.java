package springapp.page;

import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class LoginPage {

    private final WebDriverWait wait;

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Invalid credentials']")
    private WebElement invalidCredentialsLabel;

    @FindBy(css = ".orangehrm-login-forgot-header")
    private WebElement forgotPasswordButton;

    public LoginPage() {
        initElements(getDriver(), this);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));
    }

    public void enterUsernameInput(String login) {
        usernameInput.sendKeys(login);
    }

    public void enterPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickForgotPasswordButton() {
        forgotPasswordButton.click();
    }

    public void assertionGetInvalidCredentialsText() {
        wait.until(ExpectedConditions.visibilityOf(invalidCredentialsLabel));
        Assertions.assertEquals("Invalid credentials", invalidCredentialsLabel.getText());
    }
}