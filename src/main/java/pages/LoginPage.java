package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /*    wait.until(ExpectedConditions.alertIsPresent()); // Wait for the alert to be present
        Alert alert = driver.switchTo().alert(); // Switch to the alert
        String alertText = alert.getText(); // Get the text of the alert
        alert.accept(); // Accept the alert to close it
        return alertText;*/

    public void openPage() {
       driver.get("https://gym-frontend-team6-gym-frontend-dev.shared.edp-dev.cloudmentor.academy");
        driver.manage().window().maximize();
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name ='email']")));
        emailField.sendKeys(email);
    }


    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name ='password']")));
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        loginButton.click();
    }

    public String successMessage() {
        WebElement successMsgElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(text(),'Successfully Logged in')])")));
        return successMsgElement.getText();
    }
    public WebElement getSuccessMessageElement() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(text(),'Successfully Logged In')])")));
    }

    public String errorMessage() {
        WebElement inCorrectEmailOrPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(text(),'Incorrect username or password')])[1]")));
        return inCorrectEmailOrPassword.getText();
    }

    public String inValidEmailErrorMessage() {
        WebElement inValidEmail = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), 'Invalid email address')]")));
        return inValidEmail.getText();
    }

    public String inValidPasswordErrorMessage() {
        WebElement inValidPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), 'Your password must be 8-16 characters long')]")));
        return inValidPassword.getText();

    }

    public String emptyEmailErrorMessage() {
        WebElement emptyEmail = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), 'Email address is required')]")));
        return emptyEmail.getText();
    }
    public String emptyPasswordErrorMessage() {
        WebElement emptyPassword = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(), 'Password is required')]")));
        return emptyPassword.getText();
    }
//1

    public void clickOnProfileButton() {
        WebElement profileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button' and contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiIconButton-root')])[2]")));
        ////button[@type='button' and contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiIconButton-root')][2]
        profileButton.click();
    }

    public void clickLogoutButton() {
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Log Out']")));
        logoutButton.click();
    }
    public String logoutMessage() {
        WebElement logoutMsgElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(text(),'Successfully Logged Out')])[1]")));
        return logoutMsgElement.getText();
    }

    public WebElement getLogoutMessageElement() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(text(),'Successfully Logged Out')])[1]")));
    }

    public WebElement getErrorMessageElement() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(text(),'Incorrect username or password')])[1]")));
    }

}