package audioPlayer;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 1/27/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class AudioPlayer {

    private List<URI> playQueue;
    private MediaPlayer mediaPlayer;
    private boolean playing;

    public AudioPlayer() {
        playQueue = new ArrayList<URI>();
        playing = false;
    }


    public void playSong(final URI uri) {
        new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    if(playing) {
                        mediaPlayer.stop();
                    }
                    playing = true;
                    final Media media = new Media(uri.toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(1);
                    mediaPlayer.play();
                    mediaPlayer.setOnEndOfMedia(new Runnable() {
                        @Override
                        public void run() {
                            playing = false;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void pauseSong() {
        new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    mediaPlayer.pause();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void resumeSong() {
        new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    mediaPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public double getTrackTime() {
        return mediaPlayer.getCurrentTime().toMillis();
    }

}
