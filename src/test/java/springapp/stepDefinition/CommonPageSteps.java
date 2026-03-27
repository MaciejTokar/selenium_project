package springapp.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import springapp.page.CommonPage;

public class CommonPageSteps {
    private final CommonPage commonPage;

    public CommonPageSteps(CommonPage commonPage) {
        this.commonPage = commonPage;
    }

    @And("I enter name {string} into Employee Name input and confirm")
    public void i_enter_name_into_employee_name_input_and_confirm(String name) {
        commonPage
                .enterEmployeeNameInput(name)
                .clickEmployeeNameOption(name);
    }

    @Then("Confirmation is displayed")
    public void confirmation_is_displayed() {
        commonPage.assertionSuccessPopUpDisplay();
    }
}