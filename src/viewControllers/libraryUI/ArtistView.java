package viewControllers.libraryUI;

import library.Albumn;
import library.Artist;
import viewControllers.libraryUI.actions.AlbumnSelectedAction;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArtistView extends LibraryView{

    Artist artist;

    public ArtistView(LibraryViewController libraryViewController, Artist artist) {
        super(libraryViewController);
        this.artist = artist;
        init();
    }

    @Override
    public void init() {
       for(Albumn albumn : artist.getAlbumns()){
           add(new JButton(new AlbumnSelectedAction(albumn, libraryViewController)));
       }
    }

}
