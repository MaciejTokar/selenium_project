package springapp.actions;

import springapp.page.LeavePage;
import springapp.page.CommonPage;

import java.util.Map;

import static org.openqa.selenium.support.PageFactory.initElements;
import static springapp.driverSingleton.DriverConfiguration.getDriver;

public class LeavePageActions extends LeavePage {

    private final CommonPage commonPage;

    public LeavePageActions() {
        initElements(getDriver(), this);
        commonPage = new CommonPage();
    }

    public LeavePageActions enterAssignLeaveDetails(Map<String, String> dataMap) {

        String name = requireNotBlank(dataMap.get("name"));
        String options = requireNotBlank(dataMap.get("options"));
        String fromDate = requireNotBlank(dataMap.get("fromDate"));
        String toDate = requireNotBlank(dataMap.get("toDate"));
        String partial = requireNotBlank(dataMap.get("partial"));
        String duration = requireNotBlank(dataMap.get("duration"));

        commonPage.enterEmployeeNameInput(name);
        commonPage.clickEmployeeNameOption(name);

        clickLeaveTypeDropDown();
        selectLeaveTypeFromList(options);

        enterFromDateInput(fromDate);
        enterToDateInput(toDate);
        clickPartialDaysDropDown();
        clickPartialDaysDropDown();
        selectPartialDaysFromList(partial);
        clickDurationDropDown();
        selectDurationFromList(duration);
        return this;
    }
}