package springapp.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import springapp.page.LeavePage;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Collections;
import java.util.stream.Stream;

import static springapp.page.enums.LeaveType.listOfRequiredLeaveTypes;

public class LeavePageSteps extends BasePageSteps {
    private final LeavePage leavePage;

    public LeavePageSteps() {
        leavePage = new LeavePage();
    }

    @Given("I click on 'Assign Leave' button of navigation")
    public void i_click_on_assign_leave_button_of_navigation() {
        leavePage.clickAssignLeaveButton();
    }

    @And("I select option {string} in Leave Type dropdown")
    public void i_select_option_in_leave_type_dropdown(String option) {
        leavePage.clickLeaveTypeDropDown();
        if (option.regionMatches(true, 0, "CAN", 0, 3)) {
            leavePage.clickCanOption();
        } else if (option.regionMatches(true, 0, "US", 0, 2)) {
            leavePage.clickUsOption();
        } else {
            throw new AssertionError("Invalid option for leave type");
        }
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
    public void i_select_option_in_partial_days_dropdown(String option) throws InterruptedException {
        leavePage.clickPartialDaysDropDown();
        if (option.equalsIgnoreCase("All Days")) {
            leavePage.clickAllDaysOption();
        } else {
            throw new AssertionError("Invalid option for partial days");
        }
    }

    @And("I select option {string} in Duration dropdown")
    public void i_select_option_in_duration_dropdown(String option) {
        leavePage.clickDurationDropDown();
        if (option.equalsIgnoreCase("Half Day - Morning")) {
            leavePage.clickHalfDayMorningOption();
        } else {
            throw new AssertionError("Invalid option for duration");
        }
    }

    @And("I click 'Assign' button")
    public void i_click_assign_button() {
        leavePage.clickAssignButton();
    }

    @And("I click 'Ok' confirm button")
    public void i_click_ok_confirm_button() {
        leavePage.clickConfirmButton();
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
