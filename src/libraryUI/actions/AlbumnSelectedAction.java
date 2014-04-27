package libraryUI.actions;

import library.Albumn;
import library.Artist;
import libraryUI.AlbumnView;
import libraryUI.LibraryView;
import libraryUI.LibraryViewController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlbumnSelectedAction extends AbstractAction{

    private  Albumn albumn;
    private LibraryViewController libraryViewController;

    public AlbumnSelectedAction(Albumn albumn, LibraryViewController libraryViewController) {
        super(albumn.getName());
        this.libraryViewController = libraryViewController;
        this.albumn =  albumn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LibraryView view = new AlbumnView(libraryViewController, albumn);
        libraryViewController.switchView(view);
    }
}
