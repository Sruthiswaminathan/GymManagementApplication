package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "fullName")
    public WebElement nameField;

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"root\"]/div[1]/div[1]/div/form/div[4]")
    WebElement targetDropdown;

    @FindBy(xpath="//*[@id=\"root\"]/div[1]/div[1]/div/form/div[5]")
    WebElement preferableActivityDropdown;

    @FindBy(xpath = "//button[contains(text(), 'Create An Account')]")
    WebElement createAccountButton;

    @FindBy(xpath = "//span[contains(text(), 'LOGIN HERE')]")
    WebElement loginLink;

    @FindBy(xpath = "//span[contains(text(), 'CREATE NEW ACCOUNT')]")
    WebElement registerLink;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMinutes(10));
        PageFactory.initElements(driver, this);
    }
    public void clickCreateNewAccount() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        button.click();
    }
    public boolean isNameFieldPresent() {
        return wait.until(ExpectedConditions.visibilityOf(nameField)).isDisplayed();
    }
    public boolean isEmailFieldPresent() {
        return wait.until(ExpectedConditions.visibilityOf(emailField)).isDisplayed();
    }
    public boolean isPasswordFieldPresent() {
        return wait.until(ExpectedConditions.visibilityOf(passwordField)).isDisplayed();
    }
    public boolean isTargetDropdownPresent() {
        return wait.until(ExpectedConditions.visibilityOf(targetDropdown)).isDisplayed();
    }
    public boolean isPreferableActivityDropdownPresent() {
        return wait.until(ExpectedConditions.visibilityOf(preferableActivityDropdown)).isDisplayed();
    }
    public boolean isCreateAccountButtonPresent() {
        return wait.until(ExpectedConditions.visibilityOf(createAccountButton)).isDisplayed();
    }
    public boolean isLoginLinkPresent() {
        return wait.until(ExpectedConditions.visibilityOf(loginLink)).isDisplayed();
    }


    //TC02
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
    }
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }
    public void selectTargetDropdown(String option) {
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOf(targetDropdown));
        dropdownElement.click();
        WebElement optionElement = dropdownElement.findElement(By.xpath("//li[contains(@role,'option') and text()='" + option + "']"));
        optionElement.click();
    }

    public void selectPreferableActivityDropdown(String option) {
        WebElement dropdownTrigger = wait.until(ExpectedConditions.visibilityOf(preferableActivityDropdown));
        dropdownTrigger.click();
        WebElement dropdownOptionsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[contains(@class, 'MuiMenu-list')]")
        ));
        System.out.println(dropdownOptionsContainer.getAttribute("outerHTML"));
        WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//ul[contains(@class, 'MuiMenu-list')]//li[@role='option' and @data-value='" + option + "']")
        ));
        optionElement.click();
    }
    public void submitForm(){
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton)).click();
    }

    //TC03
    public boolean errorMessage(String Message){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String xpathExpression = String.format("//div[@role='alert' and contains(text(),'%s')]", Message);
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


    //TC04
    public boolean verifyMandatoryFieldErrors() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id=':r2:-helper-text' and contains(text(), 'Name is required.')]")));
            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id=':r3:-helper-text' and contains(text(), 'Email address is required.')]")));
            WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id=':r4:-helper-text' and contains(text(), 'Password is required.')]")));
            return nameError.isDisplayed() && emailError.isDisplayed() && passwordError.isDisplayed();

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

    //TC05
    public boolean verifyEmailFieldErrors(String Message) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String xpathExpression = String.format("//p[@id=':r3:-helper-text' and contains(text(), '%s')]", Message);
            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            return emailError.isDisplayed();

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

    //TC06
    public boolean verifyPasswordFieldErrors(String Message) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            String xpathExpression = String.format("//p[@id=':r4:-helper-text' and contains(text(), '%s')]", Message);
            WebElement passwordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            return passwordError.isDisplayed();

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

    //TC07
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
    public boolean isLoginPage() {
        return driver.getCurrentUrl().contains("login") || driver.getTitle().contains("Login");
    }

    //TC08
    public boolean verifyNameFieldErrors1() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id=':r2:-helper-text' and contains(text(), 'First Letter of name should be Capital.')]")));
            return nameError.isDisplayed();

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
    //TC09
    public boolean verifyNameFieldErrors2() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id=':r2:-helper-text' and contains(text(), 'Please enter a valid name. Only alphabetic characters and spaces are allowed .')]")));
            return nameError.isDisplayed();

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

    //TC10
    public boolean verifyTabOrder() {
        nameField.sendKeys(Keys.TAB);
        WebElement focusedElement = driver.switchTo().activeElement();
        if (!focusedElement.equals(emailField)) return false;

        focusedElement.sendKeys(Keys.TAB);
        focusedElement = driver.switchTo().activeElement();
        if (!focusedElement.equals(passwordField)) return false;

        focusedElement.sendKeys(Keys.TAB);
        focusedElement = driver.switchTo().activeElement();
        if (!focusedElement.equals(targetDropdown)) return false;

        focusedElement.sendKeys(Keys.TAB);
        focusedElement = driver.switchTo().activeElement();
        if (!focusedElement.equals(preferableActivityDropdown)) return false;
        return true;
    }

    //TC11
    public void togglePasswordVisibility() {
        WebElement visibilityToggleButton = driver.findElement(By.xpath("//button[@data-testid='toggle-password']"));
        visibilityToggleButton.click();
    }

}


