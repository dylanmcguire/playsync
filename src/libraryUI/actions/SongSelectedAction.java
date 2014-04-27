package libraryUI.actions;


import library.SongWrapper;
import libraryUI.LibraryView;
import libraryUI.LibraryViewController;
import libraryUI.SongView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class SongSelectedAction extends AbstractAction{
    private SongWrapper songWrapper;
    private LibraryViewController libraryViewController;

    public SongSelectedAction(SongWrapper songWrapper, LibraryViewController libraryViewController) {
        super(songWrapper.getSongTitle());
        this.libraryViewController = libraryViewController;
        this.songWrapper = songWrapper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final LibraryView songView = new SongView(songWrapper, libraryViewController);
        libraryViewController.switchView(songView);
        libraryViewController.handleSongPlayback(songWrapper);
    }
}
