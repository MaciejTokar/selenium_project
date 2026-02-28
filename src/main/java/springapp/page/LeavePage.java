package springapp.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.ConfigHelper.getTimeoutDuration;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class LeavePage extends BasePage {
    private final WebDriverWait wait;

    public LeavePage() {
        initElements(getDriver(), this);
        wait = new WebDriverWait(getDriver(), getTimeoutDuration());
    }

    @FindBy(xpath = "//li[7]/a[@class='oxd-topbar-body-nav-tab-item']")
    private WebElement assignLeaveButton;

    @FindBy(css = ".oxd-select-text")
    private WebElement leaveTypeDropDown;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option'][3]/span")
    private WebElement canOption;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option'][7]/span")
    private WebElement usOption;

    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//div[@class='oxd-date-input'])[1]/input")
    private WebElement fromDate;

    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//div[@class='oxd-date-input'])[2]/input")
    private WebElement toDate;

    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//div[@class='oxd-select-wrapper'])[2]/div/div[2]")
    private WebElement partialDaysDropDown;

    @FindBy(xpath = "//div[@role='listbox']//span[normalize-space()='All Days'] | //div[@role='listbox']//div[@role='option' and normalize-space()='All Days']")
    private WebElement allDaysOption;

    @FindBy(xpath = "(//div[contains(@class,'oxd-input-group')]//div[@class='oxd-select-wrapper'])[3]/div/div[2]")
    private WebElement durationDropDown;

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option'][2]")
    private WebElement halfDayMorningOption;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement assignButton;

    @FindBy(xpath = "//div[@class='orangehrm-modal-footer']/button[2]")
    private WebElement confirmButton;

    @FindAll(@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option']/span"))
    private List<WebElement> listOfLeaveType;

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
        typeText(fromDate, date);
        return this;
    }

    public LeavePage enterToDateInput(String date) {
        typeText(toDate, date);
        return this;
    }

    public LeavePage clickPartialDaysDropDown() throws InterruptedException {
//        Thread.sleep(Duration.ofMillis(6000));
        doubleClickButton(partialDaysDropDown);
        return this;
    }

    public LeavePage clickAllDaysOption() throws InterruptedException {
//        Thread.sleep(Duration.ofMillis(6000));
        clickButton(allDaysOption);
//        allDaysOption.click();
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
//        try {
//            wait.until(d -> false);
//        } catch (TimeoutException ignored) {
//            clickButton(confirmButton);
//        }
        return this;
    }

    public List<String> getListOfLeaveTypes() {
         return listOfLeaveType.stream()
                 .map(WebElement::getText)
                 .collect(Collectors.toList());
    }
}
