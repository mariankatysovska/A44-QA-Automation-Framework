package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SongsPage extends BasePage {


    public SongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    By pauseBtn = By.cssSelector("[data-testid='pause-btn']");
    By equalizer = By.cssSelector("[alt='Sound bars']");

    @FindBy(css = "[title='Play or resume']")
    WebElement playPauseButton;

    public boolean isPauseBtnDisplayed(){
        return driver.findElement(pauseBtn).isDisplayed();
    }

    public boolean isEqualizerDisplayed(){
        return driver.findElement(equalizer).isDisplayed();
    }

    public void startPlaySong() {
       // WebElement buttonPlayOrResume = driver.findElement(By.cssSelector("[title='Play or resume']"));
        new Actions(driver)
                .moveToElement(playPauseButton)
                .perform();
        playPauseButton.click();
    }

    public void searchForSong(String text) {
        WebElement searchInput = driver.findElement(By.cssSelector("[type='search']"));
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public String getSongName(){
        WebElement songName = driver.findElement(By.cssSelector("#playlistWrapper .song-item .title"));
        String songText = songName.getText();
        return songText;
    }

    public void clickFirstSearchResultSong() {
        List<WebElement> songsInResults = driver.findElements(By.cssSelector(".search-results .song-item .title"));
        songsInResults.get(0).click();
    }

    public void clickAddToPlaylistBtn() {
        WebElement addToBtn = driver.findElement(By.cssSelector("[data-test='add-to-btn']"));
        addToBtn.click();
    }
}
