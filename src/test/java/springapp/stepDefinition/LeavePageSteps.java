package springapp.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import springapp.page.LeavePage;
import io.cucumber.java.en.Given;
import springapp.page.CommonPage;
import io.cucumber.datatable.DataTable;
import org.junit.jupiter.api.Assertions;
import springapp.actions.LeavePageActions;

import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static springapp.page.enums.LeaveType.listOfRequiredLeaveTypes;

public class LeavePageSteps extends CommonPage {
    private final LeavePage leavePage;
    private final LeavePageActions leavePageActions;

    public LeavePageSteps() {
        super();
        leavePage = new LeavePage();
        leavePageActions = new LeavePageActions();
    }

    @Given("I click on 'Assign Leave' button of navigation")
    public void i_click_on_assign_leave_button_of_navigation() {
        leavePage.clickAssignLeaveButton();
    }

    @And("I select option {string} in Leave Type dropdown")
    public void i_select_option_in_leave_type_dropdown(String option) {
        leavePage.clickLeaveTypeDropDown()
                .selectLeaveTypeFromList(option);
    }

    @And("I enter date {string} into From Date input")
    public void i_enter_date_into_from_date_input(String date) {
        leavePage.enterFromDateInput(date);
    }

    @And("I enter date {string} into To Date input")
    public void i_enter_date_into_to_date_input(String date) {
        leavePage.enterToDateInput(date);
    }

    @And("I select option {string} in Partial Days dropdown")
    public void i_select_option_in_partial_days_dropdown(String option) {
        leavePage.clickPartialDaysDropDown()
                .selectPartialDaysFromList(option);
    }

    @And("I select option {string} in Duration dropdown")
    public void i_select_option_in_duration_dropdown(String option) {
        leavePage.clickDurationDropDown()
                .selectDurationFromList(option);
    }

    @And("I click 'Assign' button")
    public void i_click_assign_button() {
        leavePage.clickAssignButton();
    }

    @And("I click 'Ok' confirm button")
    public void i_click_ok_confirm_button() {
        leavePage.clickConfirmButton();
    }

    @When("I enter Assign Leave details")
    public void i_enter_assign_leave_details(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

        leavePageActions.enterAssignLeaveDetails(dataMap);
    }

    @Then("Validate required options of Leave Type")
    public void validate_required_options_of_leave_type() {
        leavePage.clickLeaveTypeDropDown();
        List<String> listOfLeaveTypes = leavePage.getListOfLeaveTypes();
        List<String> listOfRequiredLeaveTypes = listOfRequiredLeaveTypes();
        Collections.sort(listOfLeaveTypes);
        Collections.sort(listOfRequiredLeaveTypes);
        List<String> listOfUnexpectedLeaveTypes = Stream.concat(listOfLeaveTypes.stream(), listOfRequiredLeaveTypes.stream())
                .filter(e -> !(listOfLeaveTypes.contains(e) && listOfRequiredLeaveTypes.contains(e)))
                .distinct()
                .toList();

        listOfLeaveTypes.forEach(LOGGER::info);

        Assertions.assertEquals(listOfRequiredLeaveTypes, listOfLeaveTypes,
                "Leave Type error:\nexpected values: " + listOfRequiredLeaveTypes + "\nactual values: " + listOfLeaveTypes + "\nunexpected values: " + listOfUnexpectedLeaveTypes);
    }

    @Then("Validate required types {string} of Leave Type")
    public void validate_required_types_of_leave_type(String type) {
        leavePage.clickLeaveTypeDropDown();
        List<String> listOfLeaveTypes = leavePage.getListOfLeaveTypes();

        Assertions.assertTrue(listOfLeaveTypes.contains(type), "List doesn't contains expected type");
    }
}
