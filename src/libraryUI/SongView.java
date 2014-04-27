package libraryUI;

import library.Artist;
import library.Library;
import library.SongWrapper;
import libraryUI.actions.ArtistSelectedAction;
import libraryUI.actions.PauseSongAction;
import libraryUI.actions.ResumeSongAction;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SongView extends LibraryView {
    private SongWrapper song;

    public SongView(SongWrapper song, LibraryViewController libraryViewController) {
        super(libraryViewController);
        this.song = song;
        init();
    }

    @Override
    public void init() {
        add(new JButton(new PauseSongAction(libraryViewController)));
        add(new JButton(new ResumeSongAction(libraryViewController)));
    }
}
