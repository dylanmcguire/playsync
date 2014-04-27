package library;

import gmusic.api.model.Song;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class SongWrapper {

    private Song song;

    public SongWrapper(Song song) {
        this.song = song;
    }

    public String getSongTitle() {
        return song.getTitle();
    }

    public Song getSong() {
        return song;
    }

}
