package uitesting.runnerclasses;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)@CucumberOptions(features = {"src/test/resources/uitestingfeaturefiles/login.feature"},
        glue = {"uitesting/stepdefinationclasses"},
        tags="@valid",
        plugin = {"pretty", "json:build/reports/cucumber/report.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class LoginTestRunner {

}
//@validatelogout"