package springapp.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class MyInfoPage extends BasePage {

    @FindBy(css = "a[href*='viewMyDetails']")
    private WebElement myInfoButton;

    @FindAll(@FindBy(css = ".orangehrm-tabs-item"))
    private List<WebElement> listOfMyInfoMenu;

    public MyInfoPage() {
        initElements(getDriver(), this);
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