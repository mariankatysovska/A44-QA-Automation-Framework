import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.PlaylistPage;
import pages.SongsPage;

import java.util.List;

public class SongsTests extends BaseTest {

    @Test
    public void checkVisibilityTest() {
        LoginPage loginPage = new LoginPage(getThreadLocal());
        loginPage.login("demo@class.com", "te$t$tudent");
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));
        String text = title.getText();
        System.out.println(text);
        System.out.println("Is element invisible? === " + wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("title"))));
      //  WebElement title2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("title")));  // should fail
    }

    @Test
    public void addSongToPlaylist() {
        LoginPage loginPage = new LoginPage(getThreadLocal());
        PlaylistPage playlistPage = new PlaylistPage(getThreadLocal());
        SongsPage songsPage = new SongsPage(getThreadLocal());
        BasePage basePage = new BasePage(getThreadLocal());
        String text = "Dark Days";
        String playlistName = playlistPage.generateRandomPlaylistName();
        loginPage.login("demo@class.com", "te$t$tudent");
        songsPage.searchForSong(text);
        basePage.clickViewAllBtn();
        songsPage.clickFirstSearchResultSong();
        songsPage.clickAddToPlaylistBtn();
        playlistPage.createNewPlaylistWhileAddingSong(playlistName);
        // assertions - success banner and song name in playlist
        Assert.assertTrue(basePage.isSuccessBannerDisplayed());
        Assert.assertEquals(text, songsPage.getSongName());
    }

    @Test
    public void playSong() {
        LoginPage loginPage = new LoginPage(getThreadLocal());
        SongsPage songsPage = new SongsPage(getThreadLocal());
        loginPage.login("demo@class.com", "te$t$tudent");
        songsPage.startPlaySong();
        Assert.assertTrue(songsPage.isPauseBtnDisplayed());
        Assert.assertTrue(songsPage.isEqualizerDisplayed());
    }

}
