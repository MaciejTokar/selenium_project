package springapp.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class CommonPage extends BasePage {

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

    @FindBy(css = ".oxd-toast--info")
    private WebElement infoPopUp;

    public CommonPage() {
        initElements(getDriver(), this);
    }

    public CommonPage enterEmployeeNameInput(String name) {
        waitHelper.waitForVisibility(employeeNameInput);
        if (name.equalsIgnoreCase("valid")) {
            typeText(employeeNameInput, userName.getText());
        } else if (name.equalsIgnoreCase("invalid")) {
            typeText(employeeNameInput, "/.,[]");
        }
        return this;
    }

    public CommonPage clickEmployeeNameOption(String name) {
        if (name.equalsIgnoreCase("invalid")) {
            waitHelper.runAfterTimeout(() -> clickButton(employeeNameOption));
            waitHelper.waitForVisibility(invalidEmployeeNameLabel);
            assertTrue(invalidEmployeeNameLabel.isDisplayed(), "Validation of employee name hasn't been displayed");
        } else if (name.equalsIgnoreCase("valid")) {
            waitHelper.runAfterTimeout(() -> clickButton(employeeNameOption));
        }

        return this;
    }

    public CommonPage assertionSuccessPopUpDisplay() {
        waitHelper.waitForVisibility(successPopUp);
        assertTrue(successPopUp.isDisplayed(), "After creating user account success confirmation is displayed");
        return this;
    }

    public CommonPage assertionInfoPopUpDisplay() {
        waitHelper.waitForVisibility(infoPopUp);
        assertTrue(infoPopUp.isDisplayed(), "After searching user account info is displayed");
        return this;
    }
}