package libraryUI.actions;

import libraryUI.LibraryViewController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class PauseSongAction extends AbstractAction {

    private LibraryViewController viewController;
    public PauseSongAction(LibraryViewController viewController) {
        super("Pause");
        this.viewController = viewController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        viewController.handleSongPause();
    }
}
