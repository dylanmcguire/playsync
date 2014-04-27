package libraryUI;

import audioPlayer.AudioPlayer;
import library.Library;
import library.SongWrapper;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class LibraryViewController {

    private Library library;
    private LibraryView libraryView;
    private JPanel container;
    private AudioPlayer audioPlayer;

    public LibraryViewController(Library library) {
        this.library = library;
        container = new JPanel();
        libraryView = new BaseLibraryView(library, this);
        container.add(libraryView);
        audioPlayer = new AudioPlayer();
    }

    public void switchView(LibraryView view) {
        container.removeAll();
        container.add(view);
        container.validate();
        container.repaint();
    }

    public void handleSongPause() {
        audioPlayer.pauseSong();
    }

    public void handleResumeSong() {
        audioPlayer.resumeSong();
    }

    public void handleSongPlayback(SongWrapper songWrapper){
        try {
            URI resource = library.getURLForSong(songWrapper);
            audioPlayer.playSong(resource);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public JComponent getView(){
        return container;
    }

}
