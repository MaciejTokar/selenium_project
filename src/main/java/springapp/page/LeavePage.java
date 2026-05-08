package springapp.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;
import static springapp.driverSingleton.ConfigHelper.getTimeoutDuration;

public class LeavePage extends CommonPage {

    private final WebDriverWait webDriverWait;

    @FindBy(xpath = "//li[7]/a[@class='oxd-topbar-body-nav-tab-item']")
    private WebElement assignLeaveButton;

    @FindBy(css = ".oxd-select-text")
    protected WebElement leaveTypeDropDown;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option'][3]/span")
    private WebElement canOption;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option'][7]/span")
    private WebElement usOption;

    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//div[@class='oxd-date-input'])[1]/input")
    protected WebElement fromDateElement;

    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//div[@class='oxd-date-input'])[2]/input")
    protected WebElement toDateElement;

    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//div[@class='oxd-select-wrapper'])[2]/div/div[2]")
    protected WebElement partialDaysDropDown;

    @FindBy(xpath = "//div[@role='listbox']//span[normalize-space()='All Days'] | //div[@role='listbox']//div[@role='option' and normalize-space()='All Days']")
    private WebElement allDaysOption;

    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//div[@class='oxd-select-wrapper'])[3]/div/div[2]")
    protected WebElement durationDropDown;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option'][2]")
    protected WebElement halfDayMorningOption;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement assignButton;

    @FindBy(xpath = "//div[@class='orangehrm-modal-footer']/button[2]")
    private WebElement confirmButton;

    @FindAll(@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option']/span"))
    protected List<WebElement> listOfLeaveType;

    @FindBy(xpath = "//form/div[3]/div/div[1]/div[@class='oxd-input-group oxd-input-field-bottom-space']/span")
    private WebElement invalidFromDateLabel;

    @FindBy(xpath = "//form/div[3]/div/div[2]/div[@class='oxd-input-group oxd-input-field-bottom-space']/span")
    private WebElement invalidToDateLabel;

    @FindAll(@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option']/span"))
    protected List<WebElement> listOfPartialDays;

    @FindAll(@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option']/span"))
    protected List<WebElement> listOfDuration;

    @FindBy(css = ".oxd-userdropdown-name")
    protected WebElement userName;

    public LeavePage() {
        super();
        initElements(getDriver(), this);
        webDriverWait = new WebDriverWait(getDriver(), getTimeoutDuration());
    }

    public LeavePage clickAssignLeaveButton() {
        clickButton(assignLeaveButton);
        return this;
    }

    public LeavePage clickLeaveTypeDropDown() {
        clickButton(leaveTypeDropDown);
        return this;
    }

    public LeavePage clickCanOption() {
        clickButton(canOption);
        return this;
    }

    public LeavePage clickUsOption() {
        clickButton(usOption);
        return this;
    }

    public LeavePage enterFromDateInput(String date) {
        typeText(fromDateElement, date);
        return this;
    }

    public LeavePage enterToDateInput(String date) {
        typeText(toDateElement, date);
        return this;
    }

    public LeavePage clickPartialDaysDropDown() {
        doubleClickButton(partialDaysDropDown);
        return this;
    }

    public LeavePage clickAllDaysOption() {
        clickButton(allDaysOption);
        return this;
    }

    public LeavePage clickDurationDropDown() {
        clickButton(durationDropDown);
        return this;
    }

    public LeavePage clickHalfDayMorningOption() {
        clickButton(halfDayMorningOption);
        return this;
    }

    public LeavePage clickAssignButton() {
        clickButton(assignButton);
        return this;
    }

    public LeavePage clickConfirmButton() {
        clickButton(confirmButton);
        return this;
    }

    public List<String> getListOfLeaveTypes() {
        return listOfLeaveType.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public LeavePage selectLeaveTypeFromList(String leaveType) {
        for (WebElement option : listOfLeaveType) {
            if (option.getText().equals(leaveType)) {
                option.click();
                return this;
            }
        }
        throw new NoSuchElementException("Leave type not found " + leaveType);
    }

    public LeavePage selectPartialDaysFromList(String partial) {
        for (WebElement option : listOfPartialDays) {
            if (option.getText().equals(partial)) {
                option.click();
                return this;
            }
        }
        throw new NoSuchElementException("Partial not found " + partial);
    }

    public LeavePage selectDurationFromList(String duration) {
        for (WebElement option : listOfDuration) {
            if (option.getText().equals(duration)) {
                option.click();
                return this;
            }
        }
        throw new NoSuchElementException("Duration not found " + duration);
    }
}