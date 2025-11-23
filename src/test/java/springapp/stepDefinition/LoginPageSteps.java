package springapp.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import springapp.page.LoginPage;
import io.cucumber.java.en.Given;

public class LoginPageSteps {

    private final LoginPage loginPage;

    public LoginPageSteps() {
        loginPage = new LoginPage();
    }

    @Given("I enter login {string} into Username input")
    public void i_enter_login_into_username_input(String loginName) {
        loginPage.enterUsernameInput(loginName);
    }

    @Given("I enter incorrect login into Username input")
    public void i_enter_incorrect_login_into_username_input() {
        loginPage.enterUsernameInput("test");
    }

    @Given("I click on link 'Forgot your password?'")
    public void i_click_on_link_forgot_your_password() {
        loginPage.clickForgotPasswordButton();
    }

    @When("I click on 'Login' button")
    public void i_click_on_login_button() {
        loginPage.clickLoginButton();
    }

    @And("I enter password {string} into Password input")
    public void i_enter_password_into_password_input(String password) {
        loginPage.enterPasswordInput(password);
    }

    @And("I enter incorrect {string} into Password input")
    public void i_enter_incorrect_password_into_password_input(String invalidPassword)  {
        loginPage.enterPasswordInput(invalidPassword);
    }

    @Then("Announcement 'Invalid credentials' appear")
    public void announcement_invalid_credentials_appear() {
        loginPage.assertionGetInvalidCredentialsText();
    }
}