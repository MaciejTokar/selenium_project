package springapp.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class MyInfoPage extends BasePage {

    @FindBy(css = "a[href*='viewMyDetails']")
    private WebElement myInfoButton;

    @FindAll(@FindBy(css = ".orangehrm-tabs-item"))
    private List<WebElement> listOfMyInfoMenu;

    @FindBy(css = "a[href*='contactDetails']")
    private WebElement contactDetailsButton;

    @FindBy(css = ".oxd-form-row:nth-of-type(1) .oxd-grid-item--gutters:nth-of-type(1) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input", xpath = "//label[normalize-space()='Street 1']/following::input[1]")
//    @FindBy(xpath = "//label[normalize-space()='Street 1']/following::input[1] | ")
    private WebElement streetOneInput;

    @FindBy(css = ".oxd-form-row:nth-of-type(1) .oxd-grid-item--gutters:nth-of-type(2) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input")
    private WebElement streetTwoInput;

    @FindBy(css = ".oxd-form-row:nth-of-type(1) .oxd-grid-item--gutters:nth-of-type(3) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input")
    private WebElement cityInput;

    @FindBy(css = ".oxd-form-row:nth-of-type(1) .oxd-grid-item--gutters:nth-of-type(4) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input")
    private WebElement stateInput;

    @FindBy(css = ".oxd-form-row:nth-of-type(1) .oxd-grid-item--gutters:nth-of-type(5) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input")
    private WebElement zipInput;

    @FindBy(css = ".oxd-form-row:nth-of-type(1) .oxd-grid-item--gutters:nth-of-type(6) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) .oxd-select-wrapper")
    private WebElement countryDropDown;

//    @FindAll(@FindBy(css = ".oxd-form-row:nth-of-type(1) .oxd-grid-item--gutters:nth-of-type(6) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) .oxd-select-wrapper .oxd-select-dropdown div"))
    @FindAll(@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option']/span"))
    private List<WebElement> listOfCountryOption;
    //div[@class='oxd-select-dropdown --positon-bottom']/div[@class='oxd-select-option'][2]/span
    //div[@class='oxd-select-dropdown']/div[2]/span

    //    .oxd-form-row:nth-of-type(1) .oxd-grid-item--gutters:nth-of-type(6) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) .oxd-select-wrapper .oxd-select-dropdown div span
    @FindBy(css = ".oxd-form-row:nth-of-type(2) .oxd-grid-item--gutters:nth-of-type(1) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input")
    private WebElement homePhoneInput;

    @FindBy(css = ".oxd-form-row:nth-of-type(2) .oxd-grid-item--gutters:nth-of-type(2) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input")
    private WebElement mobilePhoneInput;

    @FindBy(css = ".oxd-form-row:nth-of-type(2) .oxd-grid-item--gutters:nth-of-type(3) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input")
    private WebElement workPhoneInput;

    @FindBy(css = ".oxd-form-row:nth-of-type(3) .oxd-grid-item--gutters:nth-of-type(1) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input")
    private WebElement workMailInput;

    @FindBy(css = ".oxd-form-row:nth-of-type(3) .oxd-grid-item--gutters:nth-of-type(2) .oxd-input-field-bottom-space:nth-of-type(1) div:nth-of-type(2) input")
    private WebElement otherMailInput;

//    @FindBy(css = ".oxd-button--text")
    @FindBy(className = "oxd-button--text")
    private WebElement addButton;

    public MyInfoPage() {
        initElements(getDriver(), this);
    }

    public MyInfoPage enterStreetOneInput(String street) {
        typeText(streetOneInput, street);
        return this;
    }

    public MyInfoPage enterStreetTwoInput(String street) {
        typeText(streetTwoInput, street);
        return this;
    }

    public MyInfoPage enterCityInput(String city) {
        typeText(cityInput, city);
        return this;
    }

    public MyInfoPage enterStateInput(String state) {
        typeText(stateInput, state);
        return this;
    }

    public MyInfoPage enterZipInput(String zip) {
        typeText(zipInput, zip);
        return this;
    }

    public MyInfoPage clickCountryDropDown() {
        clickButton(countryDropDown);
        return this;
    }

    public MyInfoPage selectCountryFromList(String country) {
        for (WebElement option : listOfCountryOption) {
            if (option.getText().equals(country)) {
                option.click();
                return this;
            }
        }
        throw new NoSuchElementException("Country not found " + country);
    }

    public MyInfoPage enterHomePhoneInput(String phone) {
        typeText(homePhoneInput, phone);
        return this;
    }

    public MyInfoPage enterMobilePhoneInput(String phone) {
        typeText(mobilePhoneInput, phone);
        return this;
    }

    public MyInfoPage enterWorkPhoneInput(String phone) {
        typeText(workPhoneInput, phone);
        return this;
    }

    public MyInfoPage enterWorkMailInput(String mail) {
        typeText(workMailInput, mail);
        return this;
    }

    public MyInfoPage enterOtherMailInput(String mail) {
        typeText(otherMailInput, mail);
        return this;
    }

    public MyInfoPage clickMyInfoButton() {
        clickButton(myInfoButton);
        return this;
    }

    public MyInfoPage assertionLinksAttached() {
        for (WebElement element : listOfMyInfoMenu) {
            assertNotNull(element.getAttribute("href"));
        }
        return this;
    }
}