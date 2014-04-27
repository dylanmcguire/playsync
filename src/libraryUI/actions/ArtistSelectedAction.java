package libraryUI.actions;

import library.Artist;
import libraryUI.ArtistView;
import libraryUI.LibraryView;
import libraryUI.LibraryViewController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArtistSelectedAction extends AbstractAction {
    private Artist artist;
    private LibraryViewController libraryViewController;

    public ArtistSelectedAction(Artist artist, LibraryViewController libraryViewController) {
        super(artist.getName());
        this.libraryViewController = libraryViewController;
        this.artist =  artist;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final LibraryView view = new ArtistView(libraryViewController, artist);
        libraryViewController.switchView(view);
    }
}
