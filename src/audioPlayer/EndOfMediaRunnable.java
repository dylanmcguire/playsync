package audioPlayer;

import library.SongWrapper;
import viewControllers.libraryUI.LibraryViewController;
import viewControllers.libraryUI.actions.SongSelectedAction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 5/13/14
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class EndOfMediaRunnable implements Runnable{

    private LibraryViewController libraryViewController;
    private AudioPlayer audioPlayer;


    public EndOfMediaRunnable(LibraryViewController libraryViewController, AudioPlayer audioPlayer) {
        this.libraryViewController = libraryViewController;
        this.audioPlayer = audioPlayer;
    }


    @Override
    public void run() {
        if(!audioPlayer.isPlayQueueEmpty()) {
            final SongSelectedAction songSelectedAction = new SongSelectedAction(audioPlayer.dequeSongWrapper(), libraryViewController);
            songSelectedAction.actionPerformed(null);
        }
    }
}