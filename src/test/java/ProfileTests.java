import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {

    @Test(groups = "ProfileTests")
    public void changeProfileName() {

        // open profile
        WebElement avatar = driver.findElement(By.cssSelector(".avatar"));
        avatar.click();
        // type password
        WebElement currentPasswordInput = driver.findElement(By.id("inputProfileCurrentPassword"));
        currentPasswordInput.click();
        currentPasswordInput.clear();
        currentPasswordInput.sendKeys("te$t$tudent");
        // type new name

        WebElement profileName = driver.findElement(By.cssSelector("#inputProfileName"));
        profileName.click();
        profileName.clear();
        profileName.sendKeys("student");
        // type email
        WebElement emailInput = driver.findElement(By.cssSelector("#inputProfileEmail"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("marianna.tysovska@gmail.com");
        // click save
        WebElement saveBtn = driver.findElement(By.cssSelector(".btn-submit"));
        saveBtn.click();
        // assert profile name is new
        driver.navigate().refresh();
        WebElement profile = driver.findElement(By.cssSelector(".view-profile>span"));
        String newName = profile.getText();
        Assert.assertEquals(newName, "student");
    }
}
