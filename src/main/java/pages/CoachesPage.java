package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CoachesPage {

    private WebDriver driver;
    private static WebDriverWait wait;


    public CoachesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public boolean isDashboardPageDisplayed() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'EnergyX')]")));
        return header.isDisplayed();
    }

    public void clickCoachesLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), 'Coaches')]")));
        link.click();
    }

    public synchronized void availableCoachesList() {

        WebElement list = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='/assets/Avatar4-BB-9-qZ5.png']")));
        list.isDisplayed();
    }
    public void nameOfCoaches() {
        WebElement name = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".MuiTypography-root.MuiTypography-h6.css-41ysla")));
        name.isDisplayed();
    }

    public void shortSummary() {
        WebElement summary = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".MuiTypography-root.MuiTypography-body2.css-14yjj7a")));
        summary.isDisplayed();
    }

    public void expertiseOrSpecialization() {
        WebElement expertise = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".MuiTypography-root.MuiTypography-body2.css-udy7fu")));
        expertise.isDisplayed();
    }

    public void clientReviewsOrRatings() {
        WebElement reviewOrRating = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".MuiTypography-root.MuiTypography-body2.css-1djp5ic")));
        reviewOrRating.isDisplayed();
    }

    public void workoutButton() {
        WebElement workoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button' and text()='Book Workout']")));
        workoutButton.isDisplayed();
    }


    public void CoachesList() {
        WebElement list = wait.until((ExpectedConditions.visibilityOfElementLocated(By.className("MuiCardContent-root css-es1jzv"))));
        list.isDisplayed();
    }

    public void clickWorkoutButton() {
        WebElement workoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button' and text()='Book Workout']")));
        workoutButton.isDisplayed();
        workoutButton.click();

    }

    public void selectDateAndTime() {
        WebElement date = wait.until((ExpectedConditions.presenceOfElementLocated(By.xpath("//button//abbr[text()='30']"))));
        date.click();
        WebElement time = wait.until((ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='04:30 - 5:30 PM']"))));
        time.click();
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)");
    }

    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-360)");
    }

    public void checkTestimonal() {
        WebElement testimonal = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h6[text()='Testimonials']")));
        testimonal.isDisplayed();
        WebElement bookWorkout = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit' and text()='Book Workout']")));
        bookWorkout.isDisplayed();
        bookWorkout.click();
    }

    public String successMessage() {
        WebElement successMsgElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Workout Created Successfully']")));
        successMsgElement.isDisplayed();
        return successMsgElement.getText();
    }

    public void seeBookings() {
        WebElement bookings = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 MuiCard-root css-1rn1fhl")));
        //cssSelector("MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation1 MuiCard-root css-19wxl77")));
        bookings.isDisplayed();
    }

    public boolean isLayoutAdaptable() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header")));
        return element.isDisplayed();
    }

    public void selectInvalidDate() {
        WebElement invalidDate = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//button[contains(@class, 'react-calendar__tile') and .//abbr[@aria-label='September 11, 2024']]"))));
        invalidDate.click();
        scrollDown();
        selectTime();
    }

    public String invalidDateErrorMessage() {
        WebElement clickBookWorkout = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit' and text()='Book Workout']")));
        clickBookWorkout.isDisplayed();
        clickBookWorkout.click();

        WebElement invalidDateError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Please Select Valid Date')]")));
        return invalidDateError.getText();
    }

    public void selectTime() {
        WebElement time = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='05:30 - 6:30 PM']")));
        time.click();
    }
}

