package springapp.page;

import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class DashboardPage {

    private final WebDriverWait wait;

    public DashboardPage() {
        initElements(getDriver(), this);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));
    }

    public String getDashboardUrl() {
        return getDriver().getCurrentUrl();
    }

    @FindBy(xpath = "//h6[normalize-space()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(css = "a[href*='viewAdminModule']")
    private WebElement adminPanelButton;

    public void clickAdminPanelButton() {
        adminPanelButton.click();
    }

    public void assertionContainsDashboardUrl() {
        String dashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        wait.until(ExpectedConditions.urlContains(dashboardUrl));
        Assertions.assertEquals(dashboardUrl, getDashboardUrl());
    }

    public void assertionGetDashboardHeaderText() {
        wait.until(ExpectedConditions.visibilityOf(dashboardHeader));
        Assertions.assertEquals("Dashboard", dashboardHeader.getText());
    }
}