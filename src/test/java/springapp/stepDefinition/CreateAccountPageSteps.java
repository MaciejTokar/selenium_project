package springapp.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import springapp.page.CreateAccountPage;

public class CreateAccountPageSteps {

    private final CreateAccountPage createAccountPage;

    public CreateAccountPageSteps() {
        createAccountPage = new CreateAccountPage();
    }

    @And("I select {string} in User Role dropdown")
    public void i_select_role_in_user_role_dropdown(String role) {
        createAccountPage.clickUserRoleDropDown()
                .selectUserRoleFromList(role);
    }

    @And("I select {string} in Status dropdown")
    public void i_select_status_in_status_dropdown(String status) {
        createAccountPage.clickStatusDropDown()
                .selectStatusFromList(status);
    }

    @And("I enter the same {string} into Confirm Password input")
    public void i_enter_the_same_password_into_confirm_password_input(String password) {
        createAccountPage.enterPasswordConfirmInput(password);
    }

    @Then("I logout from currently logged account")
    public void i_logout_from_currently_logged_account() {
        createAccountPage.clickAccountMenuDropDown()
                .clickLogoutButton();
    }
}