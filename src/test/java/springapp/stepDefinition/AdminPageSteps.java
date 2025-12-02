package springapp.stepDefinition;

import io.cucumber.java.en.And;
import springapp.page.AdminPage;
import io.cucumber.java.en.Then;

import java.util.Random;

public class AdminPageSteps {

    private final AdminPage adminPage;

    public AdminPageSteps() {
        adminPage = new AdminPage();
    }

    @And("I click 'Add' button")
    public void i_click_add_button() {
        adminPage.clickAddButton();
    }

    @And("I select option 'ESS' in User Role dropdown")
    public void i_select_option_ess_in_user_role_dropdown() {
        adminPage.clickUserRoleDropDown();
        adminPage.clickEssOption();
    }

    @And("I select option 'Enabled' in Status dropdown")
    public void i_select_option_enabled_in_status_dropdown() {
        adminPage.clickStatusDropDown();
        adminPage.clickEnabledOption();
    }

    @And("I enter 'name' into Employee Name input and confirm")
    public void i_enter_name_into_employee_name_input_and_confirm() {
        adminPage.enterEmployeeNameInput();
        adminPage.clickEmployeeNameOption();
    }

    @And("I enter {string} into Username input")
    public void i_enter_username_into_username_input(String username) {
        String randomUsername = username + new Random().nextInt(100);
        adminPage.enterUsernameInput(randomUsername);
    }

    @And("I enter {string} into Password input")
    public void i_enter_password_into_password_input(String password) {
        adminPage.enterPasswordInput(password);
    }

    @And("I enter {string} into Confirm Password input")
    public void i_enter_password_into_confirm_password_input(String password) {
        adminPage.enterPasswordConfirmInput(password);
    }

    @And("I click 'Save' button")
    public void i_click_save_button() {
        adminPage.clickSaveButton();
    }

    @Then("Confirmation is displayed and user has been created")
    public void confirmation_is_displayed_and_user_has_been_created() {
        adminPage.assertionSuccessPopUpDisplay();
    }
}