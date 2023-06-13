import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.PlaylistPage;

import java.util.List;

public class PlaylistTests extends BaseTest {

    @Test
    public void deletePlaylist() {
        LoginPage loginPage = new LoginPage(getThreadLocal());
        PlaylistPage playlistPage = new PlaylistPage(getThreadLocal());
        BasePage basePage = new BasePage(getThreadLocal());

        String playlist = playlistPage.generateRandomPlaylistName();
        loginPage.login("demo@class.com", "te$t$tudent");
        playlistPage.createNewPlaylistUsingPlusBtn(playlist);
        playlistPage.waitPlaylistHeader(playlist);
        playlistPage.clickDeletePlaylist();
        Assert.assertTrue(basePage.isSuccessBannerDisplayed());
        basePage.refreshPage();
        List<String> playlistNames = playlistPage.getPlaylistNames();
        Assert.assertFalse(playlistNames.contains(playlist));
    }


}


