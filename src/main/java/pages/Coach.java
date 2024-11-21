package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Coach {
    private WebDriver driver;
    private static WebDriverWait wait;
    private CoachesPage coachesPage;

    public Coach(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.coachesPage = new CoachesPage(driver);
    }

    public void finishWorkout() {
        coachesPage.scrollDown();
        WebElement finishWorkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Finish Workout']")));
        Assert.assertTrue(finishWorkoutButton.isDisplayed());
        finishWorkoutButton.click();
    }

    public void leaveFeedback() {
        WebElement feedback = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//button[text()='Leave Feedback'])[1]")));
        Assert.assertTrue(feedback.isDisplayed());
        feedback.click();
    }

    public void clickOnSubmit() {
        WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Submit Feedback']")));
        Assert.assertTrue(submit.isDisplayed());
        submit.click();
    }

  /*  public void successMessage() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
        Assert.assertTrue(message.isDisplayed());
    }*/


    public void finishMessage() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[span[text()='FINISHED']])[1]")));
        Assert.assertTrue(message.isDisplayed());
  //(//div[@class='MuiChip-root MuiChip-filled MuiChip-sizeMedium MuiChip-colorDefault MuiChip-filledDefault css-1rq7ngg']//span[text()='FINISHED'])[1]
    }

    public void cancelWorkout() {
        WebElement cancelWorkout=  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(text(),'Cancel Workout')])[1]")));
       Assert.assertTrue(cancelWorkout.isDisplayed());
        cancelWorkout.click();
    }

    public void confirmCancelWorkout() {
        WebElement confirmCancelWorkout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'MuiButton-containedPrimary') and text()='Cancel Workout']")));
        Assert.assertTrue(confirmCancelWorkout.isDisplayed());
        confirmCancelWorkout.click();
    }

    public void cancelledMessage() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='MuiChip-root MuiChip-filled MuiChip-sizeMedium MuiChip-colorDefault MuiChip-filledDefault css-erfook']//span[text()='CANCELED'])[1]")));
        Assert.assertTrue(message.isDisplayed());
    }

    public void clickOnClientFeedback() {
        WebElement feedback = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Client Feedback']")));
        Assert.assertTrue(feedback.isDisplayed());
        feedback.click();
    }
    public void visibleFeedback() {
        WebElement visibleClientFeedback= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiBox-root css-bv22hv']//p[text()='2024/08/15']")));
        Assert.assertTrue(visibleClientFeedback.isDisplayed());
    }

    public void successMessageOfFeedback() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
        message.isDisplayed();
    }
}
