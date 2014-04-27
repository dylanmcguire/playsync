package container;

import library.Library;
import libraryUI.LibraryViewController;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContainerGUI extends JFrame{

    final LibraryViewController libraryViewController;

    public static void main(String[] args) {
        new ContainerGUI();
    }

    public ContainerGUI() {
        final Library library = new Library();
        libraryViewController = new LibraryViewController(library);
        add(libraryViewController.getView());
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
