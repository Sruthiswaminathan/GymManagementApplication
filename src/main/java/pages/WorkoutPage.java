package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class WorkoutPage {
    private WebDriver driver;
    private static WebDriverWait wait;
    String workoutType;

    public WorkoutPage(WebDriver driver1) {
        this.driver = driver1;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isDashboardPageDisplayed() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'EnergyX')]")));
        return header.isDisplayed();
    }

    //TC02
    public boolean goToSection() {
        return driver.getCurrentUrl().equals("http://52.221.11.140/Workouts");
    }

    public boolean viewScheduledWorkout(String workoutType1, String expectedDate) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MuiCard-root")));
        List<WebElement> workoutCards = driver.findElements(By.cssSelector(".MuiCard-root"));
        boolean found = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH);
        workoutType = workoutType1;
        for (WebElement card : workoutCards) {
            WebElement workoutTitleElement = card.findElement(By.cssSelector(".MuiTypography-h5"));
            String workoutText = workoutTitleElement.getText().trim().toLowerCase();
            WebElement dateElement = card.findElement(By.cssSelector(".bold"));
            String dateText = dateElement.getText().trim();
            System.out.println("Found workout: " + workoutText);
            workoutType = workoutType1;
            System.out.println("Found date: " + dateText);
            if (workoutText.equals(workoutType.toLowerCase())) {
                LocalDate actualDate = LocalDate.parse(dateText, formatter);
                LocalDate expectedDateParsed = LocalDate.parse(expectedDate, formatter);
                if (actualDate.isEqual(expectedDateParsed)) {
                    found = true;
                    break;
                }
            }
        }

        return found;
    }

    public void cancelScheduledWorkout() {
        List<WebElement> workoutCards = driver.findElements(By.cssSelector(".MuiCard-root"));
        boolean workoutFound = false;
        for (WebElement card : workoutCards) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
            if (card.findElement(By.cssSelector(".MuiTypography-h5")).getText().equalsIgnoreCase(workoutType)) {
                workoutFound = true;
                WebElement cancelButton = card.findElement(By.xpath("//button[contains(text(), 'Cancel Workout')]"));
                cancelButton.click();
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(text(), 'Cancel Workout')]")));
                } catch (TimeoutException e) {
                    System.out.println("Dialog did not appear within the timeout.");
                    return;
                }
                try {
                    WebElement dialogCancelButton = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div[2]/button[2]"));
                    wait.until(ExpectedConditions.elementToBeClickable(dialogCancelButton));
                    dialogCancelButton.click();
                } catch (NoSuchElementException e) {
                    System.out.println("Cancel button in the dialog was not found.");
                }
                return;
            }
        }

        if (!workoutFound) {
            System.out.println("No scheduled workout found for: " + workoutType);
        }
    }

    //TC02
    public void resumeScheduledWorkout() {
        List<WebElement> workoutCards = driver.findElements(By.cssSelector(".MuiCard-root"));
        boolean workoutFound = false;
        for (WebElement card : workoutCards) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
            if (card.findElement(By.cssSelector(".MuiTypography-h5")).getText().equalsIgnoreCase(workoutType)) {
                workoutFound = true;
                WebElement cancelButton = card.findElement(By.xpath("//button[contains(text(), 'Cancel Workout')]"));
                cancelButton.click();
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(text(), 'Cancel Workout')]")));
                } catch (TimeoutException e) {
                    System.out.println("Dialog did not appear within the timeout.");
                    return;
                }
                try {
                    WebElement dialogResumeButton = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div[2]/button[1]"));
                    wait.until(ExpectedConditions.elementToBeClickable(dialogResumeButton));
                    dialogResumeButton.click();

                } catch (NoSuchElementException e) {
                    System.out.println("Resume button in the dialog was not found.");
                }
                return;
            }
        }
        if (!workoutFound) {
            System.out.println("No scheduled workout found for: " + workoutType);
        }
    }

    //TC03
    public void finishScheduledWorkout() {
        List<WebElement> workoutCards = driver.findElements(By.cssSelector(".MuiCard-root"));
        boolean workoutFound = false;
        for (WebElement card : workoutCards) {
            if (card.findElement(By.cssSelector(".MuiTypography-h5")).getText().equalsIgnoreCase(workoutType)) {
                workoutFound = true;
                WebElement finishButton = card.findElement(By.xpath("//button[contains(text(), 'Finish Workout')]"));
                finishButton.click();
            }
        }
        if (!workoutFound) {
            System.out.println("No scheduled workout found for: " + workoutType);
        }
    }

    //TC04
    public boolean viewFinishedWorkout(String workoutType1, String expectedDate) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MuiCard-root")));
        List<WebElement> workoutCards = driver.findElements(By.cssSelector(".MuiCard-root"));
        boolean found = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH);
        workoutType = workoutType1;
        for (WebElement card : workoutCards) {
            WebElement workoutTitleElement = card.findElement(By.cssSelector(".MuiTypography-h5"));
            String workoutText = workoutTitleElement.getText().trim().toLowerCase();
            WebElement dateElement = card.findElement(By.cssSelector(".bold"));
            String dateText = dateElement.getText().trim();
            System.out.println("Found workout: " + workoutText);
            workoutType = workoutType1;
            System.out.println("Found date: " + dateText);
            if (workoutText.equals(workoutType.toLowerCase())) {
                LocalDate actualDate = LocalDate.parse(dateText, formatter);
                LocalDate expectedDateParsed = LocalDate.parse(expectedDate, formatter);
                if (actualDate.isEqual(expectedDateParsed)) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public void leaveFeedback(String rating, String comments) {
        WebElement leaveFeedbackButton = driver.findElement(By.xpath("//button[text()='Leave Feedback']"));
        leaveFeedbackButton.click();
        selectRating(rating);
        WebElement commentsInput = driver.findElement(By.xpath("//textarea[@id=':r2:' and @name='notes']"));
        commentsInput.sendKeys(comments);
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit Feedback']"));
        submitButton.click();
        WebElement finishedChip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'MuiChip-filled') and contains(@class, 'MuiChip-colorDefault')]//span[text()='FINISHED']")));
        if (finishedChip.isDisplayed()) {
            System.out.println("Feedback submitted successfully: FINISHED chip is visible.");
        } else {
            System.out.println("Feedback submission failed: FINISHED chip is not visible.");
        }
    }
    private void selectRating(String rating) {
        int starRating = Integer.parseInt(rating);
        List<WebElement> starElements = driver.findElements(By.xpath("//svg[contains(@class, 'MuiSvgIcon-root') and @data-testid='StarBorderIcon']"));
        for (int i = 0; i < starRating; i++) {
            if (i < starElements.size()) {
                starElements.get(i).click();
            }
        }
    }
}

