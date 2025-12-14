package springapp.page;

import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static springapp.utils.StringGeneratorUtils.getUsername;
import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;
import static springapp.utils.StringGeneratorUtils.getGeneratedPassword;

public class AdminPage {

    private final WebDriverWait wait;
    private String confirmPassword;
    private String usernameSearch;

    @FindBy(css = ".oxd-button--secondary:nth-of-type(1)")
    private WebElement addButton;

    @FindBy(css = ".oxd-grid-item--gutters:nth-of-type(1) .oxd-select-wrapper, .oxd-grid-item--gutters:nth-of-type(2) .oxd-select-wrapper")
    private WebElement userRoleDropDown;

    @FindBy(css = ".oxd-grid-item--gutters:nth-of-type(3) .oxd-select-wrapper, .oxd-grid-item--gutters:nth-of-type(4) .oxd-select-wrapper")
    private WebElement statusDropDown;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option'][3]/span")
    private WebElement essOption;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option'][2]/span")
    private WebElement adminOption;

    @FindBy(css = ".oxd-select-option:nth-of-type(2)")
    private WebElement enabledOption;

    @FindBy(css = ".oxd-select-option:nth-of-type(3)")
    private WebElement disabledOption;

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

    @FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']/div/input[1]")
    private WebElement usernameInputSearch;

    @FindBy(css = ".oxd-grid-item--gutters:nth-of-type(2) .oxd-select-wrapper")
    private WebElement userRoleDropdownSearch;

    @FindBy(css = ".oxd-grid-item--gutters:nth-of-type(4) .oxd-select-wrapper")
    private WebElement statusDropDownSearch;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='orangehrm-background-container']/div[1]/div[1]/div[1]/h5")
    private WebElement systemUserHeader;

    @FindBy(xpath = "//div[@role='rowgroup'][2]/div/div[@role='row']/div[@role='cell'][2]/div")
    private WebElement cellUsername;

    @FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span")
    private WebElement listHeader;

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

    public void clickAdminOption() {
        adminOption.click();
    }

    public void clickStatusDropDown() {
        statusDropDown.click();
    }

    public void clickEnabledOption() {
            enabledOption.click();
    }

    public void clickDisabledOption() {
        disabledOption.click();
    }

    public void enterEmployeeNameInput() {
        employeeNameInput.sendKeys(userName.getText());
    }

    public void clickEmployeeNameOption() {
        try {
            wait.until(d -> false);
        } catch (TimeoutException ignored) {
            employeeNameOption.click();
        }
    }

    public void enterUsernameInput(String username) {
        if (username.equalsIgnoreCase("generate")) {
            usernameSearch = getUsername();
            usernameInput.sendKeys(usernameSearch);
        } else {
            usernameSearch = username;
            usernameInput.sendKeys(usernameSearch);
        }
    }

    public void enterUsernameInputSearch() {
        wait.until(ExpectedConditions.visibilityOf(systemUserHeader));
        usernameInputSearch.sendKeys(usernameSearch);
    }

    public void enterPasswordInput(String password) {
        if (password.equalsIgnoreCase("generate")) {
            confirmPassword = getGeneratedPassword();
            passwordInput.sendKeys(confirmPassword);
        } else {
            confirmPassword = password;
            passwordInput.sendKeys(confirmPassword);
        }
    }

    public void enterPasswordConfirmInput() {
        passwordConfirmInput.sendKeys(confirmPassword);
    }

    public void clickSaveButton() {
        saveButton.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void assertionSuccessPopUpDisplay() {
        wait.until(ExpectedConditions.visibilityOf(successPopUp));
        Assertions.assertTrue(successPopUp.isDisplayed(), "After creating user account success confirmation is displayed");
    }

    public void assertionUserAccountMatchingData() {
        Assertions.assertNotEquals("No Records Found", listHeader.getText().trim(), "No records found - the search didn't return any result");
        Assertions.assertEquals(usernameSearch, cellUsername.getText());
    }
}