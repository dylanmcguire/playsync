package Server;

import audioPlayer.AudioPlayer;
import netscan.status.checker.ApplicationConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerServer implements Runnable{

    private boolean connected;
    private AudioPlayer audioPlayer;

    public PlayerServer(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        connected = true;
    }

    @Override
    public void run () {
        ServerSocket listener = null;
        try {
                listener = new ServerSocket(ApplicationConstants.COMMAND_PORT_NUMBER);

                System.out.println("Awaiting connections...");

                //await play commands

                while(connected) {
                    Socket socket = listener.accept();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    int command = Integer.parseInt(input.readLine());
                    out.println("1");
                    if (command == ComConstants.PLAY_SONG) {
                        final String uriString = input.readLine();
                        out.println("1");
                        long delayTime = Long.parseLong(input.readLine()) - System.currentTimeMillis();
                        try {
                            if(delayTime > 0) {
                                Thread.sleep(delayTime);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        audioPlayer.playSong(new URI(uriString));
                    }
                    if(command == ComConstants.PAUSE_SONG) {
                        int trackPosition = Integer.parseInt(input.readLine());
                        audioPlayer.pauseSong();
                    }
                }

            } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            assert listener != null;
            try {
                listener.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}
