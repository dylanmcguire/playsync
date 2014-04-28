package viewControllers.libraryUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LibraryView extends JPanel{

    protected LibraryViewController libraryViewController;

    public LibraryView(LibraryViewController libraryViewController) {
        this.libraryViewController = libraryViewController;


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public abstract void init();

}
