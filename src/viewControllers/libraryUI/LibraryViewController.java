package viewControllers.libraryUI;

import audioPlayer.AudioPlayer;
import library.Library;
import library.SongWrapper;
import netscan.playerConnectionUI.ConnectionController;
import viewControllers.ViewController;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class LibraryViewController implements ViewController{

    private Library library;
    private LibraryView libraryView;
    private JPanel container;
    private AudioPlayer audioPlayer;
    private ConnectionController connectionController;
    private List<LibraryView> viewHistory;

    public LibraryViewController(Library library, ConnectionController connectionController, AudioPlayer audioPlayer) {
        this.library = library;
        viewHistory = new ArrayList<LibraryView>();
        container = new JPanel();
        libraryView = new BaseLibraryView(library, this);
        container.add(libraryView);
        this.audioPlayer = audioPlayer;
        this.connectionController = connectionController;
    }

    public void switchView(LibraryView view, boolean recordView) {
        if (recordView) {
            recordViewInHistory(libraryView);
        }
        libraryView = view;
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

    public void recordViewInHistory(LibraryView view) {
        viewHistory.add(view);
    }

    public void showPreviousView() {
        if (!viewHistory.isEmpty()) {
            final LibraryView prevView = viewHistory.get(viewHistory.size()-1);
            viewHistory.remove(viewHistory.size()-1);
            switchView(prevView, false);
        }
    }

    public ConnectionController getConnectionController() {
        return connectionController;
    }

    @Override
    public JComponent getView(){
        return container;
    }

    public URI toURI(SongWrapper songWrapper) throws IOException, URISyntaxException {
        return library.getURLForSong(songWrapper);
    }
}
