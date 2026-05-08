package springapp.stepDefinition;

import io.cucumber.java.en.And;
import springapp.page.CommonPage;
import springapp.page.PerformancePage;

public class PerformancePageSteps {

    private final PerformancePage performancePage;
    private final CommonPage commonPage;

    public PerformancePageSteps(CommonPage commonPage) {
        this.commonPage = commonPage;
        performancePage = new PerformancePage();
    }

    @And("I select job title {string} in Job Title dropdown")
    public void i_select_job_title_in_job_title_dropdown(String title) {
        performancePage
                .clickJobTitleDropDown()
                .selectJobTitleFromList(title);
    }

    @And("I select unit {string} in Sub Unit dropdown")
    public void i_select_unit_in_sub_unit_dropdown(String unit) {
        performancePage
                .clickSubUnitDropDown()
                .selectSubUnitFromList(unit);
    }

    @And("I select option {string} in Include dropdown")
    public void i_select_option_in_include_dropdown(String option) {
        performancePage
                .clickIncludeDropDown()
                .selectIncludeOptionFromList(option);
    }
}
