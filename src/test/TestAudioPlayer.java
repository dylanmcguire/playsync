package test;

import audioPlayer.AudioPlayer;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/29/14
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestAudioPlayer {
    public static void main(String[] args) {
        final AudioPlayer audioPlayer = new AudioPlayer();
        try {
            final URI uri = new URL("file:///home/dylan/test.mp3").toURI();
            audioPlayer.playSong(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        audioPlayer.getTrackTime();
    }
}
