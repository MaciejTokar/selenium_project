package springapp.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import springapp.page.AdminPage;
import io.cucumber.java.en.Given;

public class AdminPageSteps {

    private final AdminPage adminPage;

    public AdminPageSteps() {
        adminPage = new AdminPage();
    }

    @Given("I enter the same username which was included in account creation")
    public void i_enter_the_same_username_which_was_included_in_account_creation() {
        adminPage.enterUsernameInputSearch();
    }

    @And("I click 'Add' button")
    public void i_click_add_button() {
        adminPage.clickAddButton();
    }

    @And("I select option {string} in User Role dropdown")
    public void i_select_option_in_user_role_dropdown(String option) {
        adminPage.clickUserRoleDropDown();
        if (option.equalsIgnoreCase("ESS")) {
            adminPage.clickEssOption();
        } else if (option.equalsIgnoreCase("Admin")) {
            adminPage.clickAdminOption();
        } else {
            throw new AssertionError("Invalid option for user role");
        }

    }

    @And("I select option {string} in Status dropdown")
    public void i_select_option_enabled_in_status_dropdown(String option) {
        adminPage.clickStatusDropDown();
        if (option.equalsIgnoreCase("Enabled")) {
            adminPage.clickEnabledOption();
        } else if (option.equalsIgnoreCase("Disabled")) {
            adminPage.clickDisabledOption();
        } else {
            throw new AssertionError("Invalid option for user status");
        }
    }

    @And("I enter 'name' into Employee Name input and confirm")
    public void i_enter_name_into_employee_name_input_and_confirm() {
        adminPage
                .enterEmployeeNameInput()
                .clickEmployeeNameOption();
    }

    @And("I enter {string} into Username input")
    public void i_enter_username_into_username_input(String username) {
        adminPage.enterUsernameInput(username);
    }

    @And("I enter {string} into Password input")
    public void i_enter_password_into_password_input(String password) {
        adminPage.enterPasswordInput(password);
    }

    @And("I enter the same password into Confirm Password input")
    public void i_enter_password_into_confirm_password_input() {
        adminPage.enterPasswordConfirmInput();
    }

    @And("I click 'Save' button")
    public void i_click_save_button() {
        adminPage.clickSaveButton();
    }

    @And("I click 'Search' button")
    public void i_click_search_button() {
        adminPage.clickSearchButton();
    }

    @Then("Confirmation is displayed")
    public void confirmation_is_displayed() {
        adminPage.assertionSuccessPopUpDisplay();
    }

    @Then("Matching user account is displayed in the list")
    public void matching_user_account_is_displayed_in_the_list() {
        adminPage.assertionUserAccountMatchingData();
    }
}