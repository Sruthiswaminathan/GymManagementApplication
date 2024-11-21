package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CoachProfileEdit {
    private CoachProfileEdit coachProfileEdit;

    private WebDriver driver;
    private static WebDriverWait wait;
    private CoachesPage coachesPage;

    public CoachProfileEdit(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.coachesPage = new CoachesPage(driver);
    }

    public void clickOnEditProfile() {
        WebElement editProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Edit Account Profile']")));
        Assert.assertTrue(editProfile.isDisplayed());
        editProfile.click();
    }

    public void fillProfileForm() {
        WebElement fillName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl css-h79g6x']//input[@name='fullName']")));
        Assert.assertTrue(fillName.isDisplayed());
        fillName.click();
        fillName.sendKeys("Agarwal kapoor");

        WebElement fillTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl css-h79g6x']//input[@name='title']")));
        Assert.assertTrue(fillTitle.isDisplayed());
        fillTitle.click();
        fillTitle.sendKeys("Mrs:");
        WebElement fillAbout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-multiline css-p4ocib']//textarea[@name='summary']")));
        Assert.assertTrue(fillAbout.isDisplayed());
        fillAbout.click();
        fillAbout.sendKeys("I'm a Strength training and conditioning professional");

        coachesPage.scrollDown();

        WebElement selectSpecialization = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-1nkdfbc']//div[@role='combobox' and @id='mui-component-select-specializations']")));
        selectSpecialization.click();
        WebElement selectSpecializationOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-15d9x2v' and @data-value='nutrition-coaching']")));
        selectSpecializationOption.click();

       /* WebElement selectOutside = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='MuiTouchRipple-root css-w0pj6f']")));
        selectOutside.click();*/

    }
    public  void  uploadFile(){
        WebElement uploadFile = wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'MuiButton-outlinedPrimary') and text()='Select file']"))));
        uploadFile.click();

    }

    public void clickOnSaveChanges() {
        coachesPage.scrollDown();
        WebElement saveChanges = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save Changes']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveChanges);
     /*   WebElement saveChanges = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Save Changes']")));
        saveChanges.click();*/
    }
    public boolean isProfileUpdated() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Profile updated')]")));
        return message.isDisplayed();
    }


    public void successfullyUpdateMessage() {
        WebElement updatesSuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Profile Updated Successfully']")));
        Assert.assertTrue(updatesSuccessMessage.isDisplayed());

    }

    public void clickOnProfilePicture() {
        WebElement profilePicture = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary css-1xbptlw']")));
        profilePicture.click();
    }
}
