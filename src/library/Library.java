package library;

import gmusic.api.impl.GoogleMusicAPI;
import gmusic.api.impl.GoogleSkyJamAPI;
import gmusic.api.interfaces.IGoogleMusicAPI;
import gmusic.api.model.Song;
import sun.reflect.generics.tree.ArrayTypeSignature;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Library {

    private List<Song> songList;
    private List<Artist> artists;
    private IGoogleMusicAPI api;

    public Library(String userName, String pass) {
        api = login(userName, pass);
        artists = buildArtistList(loadSongs());

    }

    public List<Artist> getArtists() {
        return artists;
    }

    public URI getURLForSong(SongWrapper songWrapper) throws IOException, URISyntaxException {
        return api.getSongURL(songWrapper.getSong());
    }

    private List<Artist> buildArtistList(List<Song> songs) {
        List<Artist> artistList = new ArrayList<Artist>();

        for (Song song : songs) {
            String artistName = song.getAlbumArtist();
            String albumnName = song.getAlbum();
            Artist artist = getArtistWithName(artistName, artistList);
            if (artist == null) {
                artist = new Artist(artistName);
                artistList.add(artist);
            }
            Albumn albumn = getAlbumnWithName(albumnName, artist);
            if (albumn == null) {
                albumn = new Albumn(albumnName);

                artist.addAlbumn(albumn);
            }
            albumn.addSong(new SongWrapper(song));
        }
        return artistList;
    }

    private Artist getArtistWithName(String name, List<Artist> artistList) {
        for (Artist artist : artistList) {
            if (artist.getName().equals(name)) {
                return artist;
            }
        }
        return null;
    }

    private Albumn getAlbumnWithName(String name, Artist artist) {
        for (Albumn albumn : artist.getAlbumns()) {
            if (albumn.getName().equals(name)) {
                return albumn;
            }
        }
        return null;
    }

    private IGoogleMusicAPI login(String username, String password) {
        System.out.println(Calendar.getInstance().getTime());
        IGoogleMusicAPI api = new GoogleMusicAPI();

        try
        {
          //  new GoogleSkyJamAPI().login(username, password);
           // new GoogleMusicAPI().login(username, password);
            api.login(username, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return api;
    }

    private List<Song> loadSongs() {

        final List<Song> list = new ArrayList<Song>();

        try
        {

            for(Song song : api.getAllSongs()) {
                list.add(song);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return list;
    }


}
