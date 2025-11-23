package springapp.page;

import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class ForgotPasswordPage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(css = "button[type='submit']")
    private WebElement resetPasswordButton;

    @FindBy(css = ".orangehrm-forgot-password-title")
    private WebElement resetPasswordSuccessHeader;

//    @FindBy(xpath = "//span[text()='Required']") - ktos na stronie zmienil jezyk i przestalo dzialac
    @FindBy(css = ".oxd-input-field-error-message")
    private WebElement requiredWarningSpan;

    public ForgotPasswordPage() {
        initElements(getDriver(), this);
    }

    public void enterUsernameInput(String username) {
        usernameInput.sendKeys(username);
    }

    public void clickResetPasswordButton() {
        resetPasswordButton.click();
    }

    public void assertionSendResetPasswordLink() {
        Assertions.assertEquals("Reset Password link sent successfully", resetPasswordSuccessHeader.getText());
    }

    public void assertionUsernameRequired() {
        Assertions.assertEquals("Required", requiredWarningSpan.getText()); // jak ktos zmieni jezyk na stronie to przestaje dzialac
    }
}