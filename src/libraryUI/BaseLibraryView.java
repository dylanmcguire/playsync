package libraryUI;

import library.Artist;
import library.Library;
import libraryUI.actions.ArtistSelectedAction;

import javax.swing.*;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseLibraryView extends LibraryView {
    private Library library;

    public BaseLibraryView(Library library, LibraryViewController libraryViewController) {
        super(libraryViewController);
        this.library = library;
        init();
    }

    @Override
    public void init() {
        for(Artist artist : library.getArtists()) {
            add(new JButton(new ArtistSelectedAction(artist, libraryViewController)));
        }
    }
}
