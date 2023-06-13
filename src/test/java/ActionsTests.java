import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.SongsPage;

import java.util.List;

public class ActionsTests extends BaseTest {
    @Test
    public void playSongTest() {
        LoginPage loginPage = new LoginPage(getThreadLocal());
        SongsPage songsPage = new SongsPage(getThreadLocal());
        // hover over in clickPlayBtn
        loginPage.login("demo@class.com", "te$t$tudent");
        songsPage.clickFooterPlayBtn();
        Assert.assertTrue(songsPage.pauseBtnExists());

        // Comparing numbers of elements example
        List<WebElement> songs = driver.findElements(By.cssSelector("[data-test='song-card']"));

        int songsNumberBefore = songs.size();
        System.out.println(songsNumberBefore);
        // Just an example: here we would add or delete an element but we didn't
        int songsNumberAfter = songs.size();
        System.out.println(songsNumberAfter);

        // Soft assert example
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(songsNumberBefore == songsNumberAfter,
                "=== Songs number before should be equal songs number after ===");
        softAssert.assertEquals(driver.getCurrentUrl(), "https://qa.koel.app/#!/queue");
        System.out.println("Hello world");
        softAssert.assertAll();
    }

    @Test
    public void renamePlaylist() {
        LoginPage loginPage = new LoginPage(getThreadLocal());
        SongsPage songsPage = new SongsPage(getThreadLocal());
        // double click
        String playlistName = "Summer songs";

        loginPage.login("demo@class.com", "te$t$tudent");
        songsPage.doubleClickChoosePlaylist();
        songsPage.enterPlaylistName(playlistName);
        String newName = songsPage.getPlaylistName();
        Assert.assertEquals(playlistName, newName, "=== PlaylistNames expected to be equals ===");
    }

    @Test
    public void playSongFromListTest() {
        LoginPage loginPage = new LoginPage(getThreadLocal());
        SongsPage songsPage = new SongsPage(getThreadLocal());
        loginPage.login("demo@class.com", "te$t$tudent");
        songsPage.goToAllSongs();
        // right click on first song
        songsPage.rightClickOnFirstSong();
        // click play button
        songsPage.clickPlayBtn();
        // verify
        Assert.assertTrue(songsPage.isEqualizerDisplayed());
    }

    @Test
    public void countSongsInPlaylist() {
        LoginPage loginPage = new LoginPage(getThreadLocal());

        loginPage.login("demo@class.com", "te$t$tudent");
        WebElement playlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(4)")));
        playlist.click();
        List<WebElement> songs = driver.findElements(By.cssSelector("#playlistWrapper .song-item"));
        int number = songs.size();
        Assert.assertEquals(number, 1); // can fail, depends on current number. This is just an example

    }
}
