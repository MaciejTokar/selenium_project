package springapp.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;

import java.util.List;
import java.util.NoSuchElementException;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class CreateAccountPage extends BasePage {

    @FindBy(css = ".oxd-grid-item--gutters:nth-of-type(1) .oxd-select-wrapper, .oxd-grid-item--gutters:nth-of-type(2) .oxd-select-wrapper")
    private WebElement userRoleDropDown;

    @FindAll(@FindBy(css = ".oxd-grid-item--gutters:nth-of-type(1) .oxd-select-wrapper span"))
    protected List<WebElement> listOfUserRole;

    @FindBy(css = ".oxd-grid-item--gutters:nth-of-type(3) .oxd-select-wrapper, .oxd-grid-item--gutters:nth-of-type(4) .oxd-select-wrapper")
    private WebElement statusDropDown;

    @FindAll(@FindBy(css = ".oxd-grid-item--gutters:nth-of-type(3) .oxd-select-wrapper span"))
    protected List<WebElement> listOfStatus;

    @FindBy(css = ".user-password-row .oxd-grid-item--gutters:nth-of-type(2) input")
    private WebElement passwordConfirmInput;

    @FindBy(css = ".oxd-userdropdown-name")
    private WebElement accountMenuDropDown;

    @FindBy(css = "a[href*='logout']")
    private WebElement logoutButton;

    public CreateAccountPage() {
        initElements(getDriver(), this);
    }

    public CreateAccountPage clickUserRoleDropDown() {
        clickButton(userRoleDropDown);
        return this;
    }

    public CreateAccountPage selectUserRoleFromList(String userRole) {
        for (WebElement option : listOfUserRole) {
            if (option.getText().equals(userRole)) {
                option.click();
                return this;
            }
        }
        throw new NoSuchElementException("User role not found " + userRole);
    }

    public CreateAccountPage clickStatusDropDown() {
        clickButton(statusDropDown);
        return this;
    }

    public CreateAccountPage selectStatusFromList(String status) {
        for (WebElement option : listOfStatus) {
            if (option.getText().equals(status)) {
                option.click();
                return this;
            }
        }
        throw new NoSuchElementException("Status not found " + status);
    }

    public CreateAccountPage enterPasswordConfirmInput(String confirmPassword) {
        typeText(passwordConfirmInput, confirmPassword);
        return this;
    }

    public CreateAccountPage clickAccountMenuDropDown() {
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
        waitHelper.waitForUrl(url);
        clickButton(accountMenuDropDown);
        return this;
    }

    public CreateAccountPage clickLogoutButton() {
        clickButton(logoutButton);
        return this;
    }
}