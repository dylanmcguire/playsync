package test;



import gmusic.api.impl.GoogleMusicAPI;
import gmusic.api.impl.GoogleSkyJamAPI;
import gmusic.api.interfaces.IGoogleMusicAPI;
import gmusic.api.model.Song;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */



public class TestLibraryAccess
{
    public static void main(String args[])
    {
        String password = "SBgnu154!";
        String username = "dylanmcguire230";
        System.out.println(Calendar.getInstance().getTime());
        IGoogleMusicAPI api = new GoogleMusicAPI();
        // IGoogleMusicAPI api = new GoogleSkyJamAPI();
        // GoogleSkyJamAPI api = new GoogleSkyJamAPI(new ApacheConnector(), new JSON(), new File("."));
        // GoogleMusicAPI aa = new GoogleMusicAPI(new ApacheConnector(), new JSON(), new File("."));

        try
        {
            new GoogleSkyJamAPI().login(username, password);
            new GoogleMusicAPI().login(username, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            api.login(username, password);
            // QueryResponse response = api.search("Jane");
            // api.downloadSongs(response.getResults().getSongs());

            /*
			Playlists playlists = api.getAllPlaylists();
			if(playlists.getMagicPlaylists() != null)
			{
				for(Playlist list : playlists.getMagicPlaylists())
				{
					System.out.println("--- " + list.getTitle() + " " + list.getPlaylistId() + " ---");
					for(Song song : list.getPlaylist())
					{
						System.out.println(song.getName() + " " + song.getArtist());
					}
				}
			}
			*/
            for(Song song : api.getAllSongs()) {
                System.out.println(song.getTitle());
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(Calendar.getInstance().getTime());
    }

}