package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/get_user_profile.feature"},
        glue = {"stepdefinitions"},
        publish = true,
        plugin = {"pretty", "json:build/reports/cucumber/report.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})
public class GetProfileApi {

}
