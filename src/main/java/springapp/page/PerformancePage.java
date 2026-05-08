package springapp.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.ConfigHelper.getTimeoutDuration;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class PerformancePage extends CommonPage {

    private final WebDriverWait webDriverWait;

    @FindAll(@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option']/span"))
    private List<WebElement> listOfJobTitle;

    @FindAll(@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option --indent-2']/span"))
    private List<WebElement> listOfSubUnit;

    @FindAll(@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option']/span"))
    private List<WebElement> listOfInclude;

    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[1]")
    private WebElement jobTitleDropDown;

    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[4]")
    private WebElement subUnitDropDown;

    @FindBy(xpath = "(//div[contains(@class,'oxd-select-text')])[7]")
    private WebElement includeDropDown;

    public PerformancePage() {
        super();
        initElements(getDriver(), this);
        webDriverWait = new WebDriverWait(getDriver(), getTimeoutDuration());
    }

    public PerformancePage selectJobTitleFromList(String title) {
        for (WebElement option : listOfJobTitle) {
            if (option.getText().equals(title)) {
                option.click();
                return this;
            }
        }
        throw new NoSuchElementException("Title not found " + title);
    }

    public PerformancePage selectSubUnitFromList(String unit) {
        for (WebElement option : listOfSubUnit) {
            if (option.getText().equals(unit)) {
                option.click();
                return this;
            }
        }
        throw new NoSuchElementException("Unit not found " + unit);
    }

    public PerformancePage selectIncludeOptionFromList(String include) {
        for (WebElement option : listOfInclude) {
            if (option.getText().equals(include)) {
                option.click();
                return this;
            }
        }
        throw new NoSuchElementException("Include not found " + include);
    }

    public PerformancePage clickJobTitleDropDown() {
        clickButton(jobTitleDropDown);
        return this;
    }

    public PerformancePage clickSubUnitDropDown() {
        clickButton(subUnitDropDown);
        return this;
    }

    public PerformancePage clickIncludeDropDown() {
        clickButton(includeDropDown);
        return this;
    }
}
