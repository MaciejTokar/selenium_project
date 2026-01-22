package springapp.page;

import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class LoginPage extends BasePage {

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
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(2));
    }

    public LoginPage enterUsernameInput(String login) {
        typeText(usernameInput, login);
        return this;
    }

    public LoginPage enterPasswordInput(String password) {
        typeText(passwordInput, password);
        return this;
    }

    public LoginPage clickLoginButton() {
        clickButton(loginButton);
        return this;
    }

    public LoginPage clickForgotPasswordButton() {
        clickButton(forgotPasswordButton);
        return this;
    }

    public LoginPage assertionGetInvalidCredentialsText() {
        wait.until(ExpectedConditions.visibilityOf(invalidCredentialsLabel));
        assertEquals("Invalid credentials", invalidCredentialsLabel.getText());
        return this;
    }
}