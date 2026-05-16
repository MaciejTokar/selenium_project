package springapp.stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import springapp.page.MyInfoPage;

public class MyInfoPageSteps {

    private final MyInfoPage myInfoPage;

    public MyInfoPageSteps() {
        myInfoPage = new MyInfoPage();
    }

    @Given("I click 'My Info' panel on the header list")
    public void i_click_my_info_panel_on_the_header_list() {
        myInfoPage.clickMyInfoButton();
    }

    @Then("I check if every button of menu have link attached")
    public void i_check_if_every_button_of_menu_have_link_attached() {
        myInfoPage.assertionLinksAttached();
    }
}