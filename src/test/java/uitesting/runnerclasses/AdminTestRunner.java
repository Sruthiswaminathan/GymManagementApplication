package uitesting.runnerclasses;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/uitestingfeaturefiles/Admin.feature"},
        glue = {"uitesting/stepdefinationclasses"},
        plugin = {"pretty", "json:build/reports/cucumber/report.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class AdminTestRunner {
}//tags="@viewCoachesList", tags="@completeAdminFlow",tags="@AdminProfilePicture",