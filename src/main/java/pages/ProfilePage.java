package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    private WebDriver driver;
    private static WebDriverWait wait;
   /* @FindBy(xpath = "//input[@name='fullName']")
    public WebElement nameField;*/
   @FindBy(xpath="//input[@name='fullName']")
   public WebElement nameField;
    public ProfilePage(WebDriver driver1) {
        PageFactory.initElements(driver, this);
        this.driver = driver1;
        this.wait = new WebDriverWait(driver, Duration.ofMinutes(3));
    }
    public boolean isDashboardPageDisplayed() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'EnergyX')]")));
        return header.isDisplayed();
    }

    //TC01
    public WebElement getSuccessMessageElement() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']//div[text()='Profile Updated Sucessfully']")));
    }
    public WebElement getErrorMessageElement() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']//div[text()='Same Data']")));
    }
    public void clickProfileIcon() {
        WebElement profileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button'][2]")));
        profileButton.click();
    }
    public void clickMyAccounts() {
        WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"menu-appbar\"]/div[3]/ul/div/li")));
        account.click();
    }
    public void enterName(String name) {
        WebElement nameFeild = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='fullName']")));
        nameFeild.sendKeys(name);
       /* wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);*/
    }
    public void selectTargetDropdown(String option) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div.MuiBox-root.css-0 > div.MuiBox-root.css-1opp6bo > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-9.MuiGrid-grid-md-6.css-1nlpgvr > div > div > div:nth-child(3) > div > div")));
        dropdownElement.click();
        WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(@role,'option') and text()='" + option + "']")));
        optionElement.click(); // Click the target option
    }
    public void selectPreferableActivityDropdown(String option) {
        WebElement dropdownTrigger = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div.MuiBox-root.css-0 > div.MuiBox-root.css-1opp6bo > div > div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-9.MuiGrid-grid-md-6.css-1nlpgvr > div > div > div:nth-child(4) > div")));
        dropdownTrigger.click();
        WebElement crossFitOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[@class='MuiList-root MuiList-padding MuiMenu-list css-r8u8y9']/li[@data-value='" + option + "']")
        ));
        crossFitOption.click();
    }
    public void clickOnSaveChanges() {
        WebElement saveChangesButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Save Changes']")
        ));
        saveChangesButton.click();
    }
    public String successMessage() {
        WebElement successMsgElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']//div[text()='Profile Updated Sucessfully']")));
        return successMsgElement.getText();
    }
    public String errorMessage() {
        WebElement successMsgElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']//div[text()='Same Data']")));
        return successMsgElement.getText();
    }

    //TC03
    public boolean errorMessageForEmptyName() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String xpathExpression = String.format("//p[contains(text(),'Name is required.')]");
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            return errorMessage.isDisplayed();

        } catch (NoSuchElementException e) {
            System.out.println("One or more error messages were not found: " + e.getMessage());
            return false;
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for error messages to appear: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    public boolean errorMessageForWithoutCapsName() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String xpathExpression = String.format("//p[contains(text(),'First Letter of name should be Capital.')]");
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            return errorMessage.isDisplayed();

        } catch (NoSuchElementException e) {
            System.out.println("One or more error messages were not found: " + e.getMessage());
            return false;
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for error messages to appear: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    public boolean errorMessageForWithSpecialCharacters() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String xpathExpression = String.format("Please enter a valid name. Only alphabetic characters and spaces are allowed .')]");
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            return errorMessage.isDisplayed();

        } catch (NoSuchElementException e) {
            System.out.println("One or more error messages were not found: " + e.getMessage());
            return false;
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for error messages to appear: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }
    public void clickLogoutButton() {
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Log Out']")));
        logoutButton.click();
    }
    public String logoutMessage() {
        WebElement logoutMsgElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(text(),'Successfully Logged Out')])[1]")));
        return logoutMsgElement.getText();
    }

}
