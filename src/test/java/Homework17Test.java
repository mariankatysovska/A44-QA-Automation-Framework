import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework17Test extends BaseTest {

    @Test
       public void addSongToPlaylist () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");



        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        String url = "https://bbb.testpro.io/";
        driver.get(url);

        WebElement emailField = driver.findElement(By.cssSelector("['type=email’]"));
        emailField.click();
        emailField.sendKeys("marianna.tysovska@gmail.com");

        WebElement passwordField = driver.findElement(By.cssSelector("['type=password’]"));
        passwordField.click();
        passwordField.sendKeys("te$t$student");

        WebElement submitButton = driver.findElement(By.cssSelector("['type=submit’]"));
        submitButton.click();
         WebElement avatarIcon = driver.findElement(By.cssSelector("[alt='Avatar of student’]"));
         Assert.assertTrue(avatarIcon.isDisplayed());


        WebElement searchInput = driver.findElement(By.id("search-input"));
        searchInput.sendKeys("For the Poor");

        WebElement viewAllButton = driver.findElement(By.xpath("//button[contains(text(), 'View All')]"));
        viewAllButton.click();

        WebElement firstSong = driver.findElement(By.xpath("//div[@class='song-list-item'][1]"));
        firstSong.click();

        WebElement addToButton = driver.findElement(By.xpath("//button[contains(text(), 'Add To...')]"));
        addToButton.click();

        WebElement playlistOption = driver.findElement(By.xpath("//li[contains(text(), 'marianna')]"));
        playlistOption.click();
        WebElement greenBanner = driver.findElement(By.xpath("//div[@class='green-banner']"));
        String bannerText = greenBanner.getText();
        String expectedResult = "Added 1 song into marianna";
        assert bannerText.equals(expectedResult) : "Text banner is expected";










        driver.quit();
    }
}
