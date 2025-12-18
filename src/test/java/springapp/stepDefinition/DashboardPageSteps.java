package springapp.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import springapp.page.DashboardPage;

public class DashboardPageSteps {

    private final DashboardPage dashboardPage;

    public DashboardPageSteps() {
        dashboardPage = new DashboardPage();
    }

    @Given("I click 'Admin' panel on the header list")
    public void i_click_admin_panel_on_the_header_list() {
        dashboardPage.clickAdminPanelButton();
    }

    @Then("User has been successfully logged in")
    public void user_has_been_successfully_logged_in() {
        dashboardPage
                .assertionContainsDashboardUrl()
                .assertionGetDashboardHeaderText();
    }
}