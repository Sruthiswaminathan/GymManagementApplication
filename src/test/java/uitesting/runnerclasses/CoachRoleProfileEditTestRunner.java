package uitesting.runnerclasses;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/uitestingfeaturefiles/CoachRoleProfile.feature"},
        glue = {"uitesting/stepdefinationclasses"},
        tags="@clientFeedback",
        plugin = {"pretty", "json:build/reports/cucumber/report.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class CoachRoleProfileEditTestRunner {
}
// tags="@ProfileUpdate",tags="@clientFeedback",clientFeedback or @ProfileUpdate
