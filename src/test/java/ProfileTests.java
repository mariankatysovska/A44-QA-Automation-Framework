import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;

public class ProfileTests extends BaseTest {

    @Test(groups = "ProfileTests")
    public void changeProfileName() {
        LoginPage loginPage = new LoginPage(getThreadLocal());
        BasePage basePage = new BasePage(getThreadLocal());
        loginPage.login("demo@class.com","te$t$tudent");
        // open profile
        WebElement avatar = basePage.waitUntilClickable(By.cssSelector(".avatar"));
        avatar.click();
        // type password
        WebElement currentPasswordInput = driver.findElement(By.id("inputProfileCurrentPassword"));
        currentPasswordInput.click();
        currentPasswordInput.clear();
        currentPasswordInput.sendKeys("te$t$tudent");
        // type new name
        String name = basePage.generateRandomName();
        System.out.println(name);
        WebElement profileName = driver.findElement(By.cssSelector("#inputProfileName"));
        profileName.click();
        profileName.clear();
        profileName.sendKeys(name);
        // type email
        WebElement emailInput = driver.findElement(By.cssSelector("#inputProfileEmail"));
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("demo@class.com");
        // click save
        WebElement saveBtn = driver.findElement(By.cssSelector(".btn-submit"));
        saveBtn.click();
        // assert profile name is new
        driver.navigate().refresh();
        WebElement profile = driver.findElement(By.cssSelector(".view-profile>span"));
        String newName = profile.getText();
        Assert.assertEquals(newName, name);
    }

    @Test
    public void changeProfileBackground(){
        LoginPage loginPage = new LoginPage(getThreadLocal());
        BasePage basePage = new BasePage(getThreadLocal());
        loginPage.login("demo@class.com","te$t$tudent");
        // open profile

        WebElement avatar = basePage.waitUntilClickable(By.cssSelector(".avatar"));
        avatar.click();
        WebElement oakTheme = driver.findElement(By.xpath("//div[@data-testid='theme-card-oak']"));
        oakTheme.click();
        WebElement oakPanel = driver.findElement(By.xpath("//div[@data-testid='theme-card-oak'][@class='theme selected']"));
        Assert.assertTrue(oakPanel.isDisplayed());
        WebElement background = driver.findElement(By.cssSelector("html[data-theme='oak']"));
        Assert.assertTrue(background.isDisplayed());
    }
}
