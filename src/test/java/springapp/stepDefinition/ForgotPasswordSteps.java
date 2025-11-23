package springapp.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import springapp.page.ForgotPasswordPage;

public class ForgotPasswordSteps {

    private final ForgotPasswordPage forgotPasswordPage;

    public ForgotPasswordSteps() {
        forgotPasswordPage = new ForgotPasswordPage();
    }

    @And("I type {string} into Username input")
    public void i_type_username_into_username_input(String loginName) {
        forgotPasswordPage.enterUsernameInput(loginName);
    }

    @When("I click on 'Reset Password' button")
    public void i_click_on_reset_password_button() {
        forgotPasswordPage.clickResetPasswordButton();
    }

    @Then("Reset password link has been successfully sent")
    public void reset_password_link_has_been_successfully_sent() {
        forgotPasswordPage.assertionSendResetPasswordLink();
    }

    @Then("Warning 'Required' is displayed")
    public void warning_required_is_displayed() {
        forgotPasswordPage.assertionUsernameRequired();
    }
}