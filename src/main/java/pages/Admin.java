package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class Admin {

    private WebDriver driver;
    private static WebDriverWait wait;

    public Admin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public  boolean CoachesListPage(){
        WebElement CoachesListPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='MuiBox-root css-qdkpfm']//p[text()='Coaches']")));
        return CoachesListPage.isDisplayed();
    }

    public void clickViewReportButton() {
        WebElement viewReportButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space(text())='View Report']")));
        viewReportButton.click();
    }
     public  boolean reportPage(){
        WebElement reportPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[normalize-space(text())='Coach Report']")));
         return reportPage.isDisplayed();
     }

    public  void downloadReport() {
        WebElement downloadReportButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Download PDF']")));
        downloadReportButton.click();
    }

    public void verifyCoachName() {
        WebElement coachName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[p[strong[normalize-space(text())='Coach Name:']]]")));
        assertTrue(coachName.isDisplayed());
    }

    public void verifyReportWeek() {
        WebElement reportWeek = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[p[strong[contains(text(), 'Report Week:')]]]")));
        assertTrue(reportWeek.isDisplayed());
    }

    public void verifyTotalSessions() {
        WebElement totalSessions = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[p[strong[contains(text(), 'Total Sessions:')]]]")));
        assertTrue(totalSessions.isDisplayed());
    }

    public void verifyTotalDuration() {
        WebElement totalDuration = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[p[strong[contains(text(), 'Total Duration (mins):')]]]")));
        assertTrue(totalDuration.isDisplayed());
    }

    public void verifyAverageClientRating() {
        WebElement averageClientRating = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[p[strong[contains(text(), 'Average Client Rating:')]]]")));
        assertTrue(averageClientRating.isDisplayed());
    }

    public void verifyFeedbackCount() {
        WebElement feedbackCount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[p[strong[contains(text(), 'Feedback Count:')]]]")));
        assertTrue(feedbackCount.isDisplayed());
    }

    public void verifyTotalClients() {
        WebElement totalClients = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[p[strong[contains(text(), 'Total Clients:')]]]")));
        assertTrue(totalClients.isDisplayed());
    }
}
