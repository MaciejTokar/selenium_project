package springapp.page;

import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//h6[normalize-space()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(css = "a[href*='viewAdminModule']")
    private WebElement adminButton;

    @FindBy(css = "a[href*='viewLeaveModule']")
    private WebElement leaveButton;

    @FindBy(css = "a[href*='viewPerformanceModule']")
    private WebElement performanceButton;

    public DashboardPage() {
        initElements(getDriver(), this);
    }

    public String getDashboardUrl() {
        return getDriver().getCurrentUrl();
    }

    public DashboardPage clickAdminPanelButton() {
        clickButton(adminButton);
        return this;
    }

    public DashboardPage clickLeavePanelButton() {
        clickButton(leaveButton);
        return this;
    }

    public DashboardPage clickPerformancePanelButton() {
        clickButton(performanceButton);
        return this;
    }

    public DashboardPage assertionContainsDashboardUrl() {
        String dashboardUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        waitHelper.waitForUrl(dashboardUrl);
        Assertions.assertEquals(dashboardUrl, getDashboardUrl());
        return this;
    }

    public DashboardPage assertionGetDashboardHeaderText() {
        waitHelper.waitForVisibility(dashboardHeader);
        Assertions.assertEquals("Dashboard", dashboardHeader.getText());
        return this;
    }
}