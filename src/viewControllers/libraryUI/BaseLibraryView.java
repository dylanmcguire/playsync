package viewControllers.libraryUI;

import library.Artist;
import library.Library;
import viewControllers.libraryUI.actions.ArtistSelectedAction;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        List<Artist> artistList = library.getArtists();
        Collections.sort(artistList, new Comparator<Artist>() {
            @Override
            public int compare(Artist o1, Artist o2) {

                return o1.getName().compareTo(o2.getName());
            }
        });
        for(Artist artist : artistList) {
            add(new JButton(new ArtistSelectedAction(artist, libraryViewController)));
        }
    }
}
