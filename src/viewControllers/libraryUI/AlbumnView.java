package viewControllers.libraryUI;

import library.Albumn;
import library.SongWrapper;
import viewControllers.libraryUI.actions.SongSelectedAction;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AlbumnView extends LibraryView{

    Albumn albumn;

    public AlbumnView(LibraryViewController libraryViewController, Albumn albumn) {
        super(libraryViewController);
        this.albumn = albumn;
        init();
    }

    @Override
    public void init() {
        for(SongWrapper songWrapper : albumn.getSongs()){
            add(new JButton(new SongSelectedAction(songWrapper, libraryViewController)));
        }
    }

}
