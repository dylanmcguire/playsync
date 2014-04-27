package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Albumn {
    private List<SongWrapper> songs;
    private String name;

    public Albumn(String name) {
        this.name = name;
        songs = new ArrayList<SongWrapper>();
    }

    public void addSong(SongWrapper song){
        songs.add(song);
    }

    public String getName() {
        return name;
    }

    public List<SongWrapper> getSongs() {
        return songs;
    }
}
