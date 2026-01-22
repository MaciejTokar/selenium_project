package springapp.page;

import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class ForgotPasswordPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(css = "button[type='submit']")
    private WebElement resetPasswordButton;

    @FindBy(css = ".orangehrm-forgot-password-title")
    private WebElement resetPasswordSuccessHeader;

    @FindBy(css = ".oxd-input-field-error-message")
    private WebElement requiredWarningSpan;

    public ForgotPasswordPage() {
        initElements(getDriver(), this);
    }

    public ForgotPasswordPage enterUsernameInput(String username) {
        typeText(usernameInput, username);
        return this;
    }

    public ForgotPasswordPage clickResetPasswordButton() {
        clickButton(resetPasswordButton);
        return this;
    }

    public ForgotPasswordPage assertionSendResetPasswordLink() {
        Assertions.assertEquals("Reset Password link sent successfully", resetPasswordSuccessHeader.getText());
        return this;
    }

    public ForgotPasswordPage assertionUsernameRequired() {
        Assertions.assertEquals("Required", requiredWarningSpan.getText()); // jak ktos zmieni jezyk na stronie to przestaje dzialac
        return this;
    }
}