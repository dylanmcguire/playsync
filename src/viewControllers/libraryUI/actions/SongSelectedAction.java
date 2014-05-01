package viewControllers.libraryUI.actions;


import Server.ComConstants;
import netscan.connectionsModel.ConnectionModel;
import library.SongWrapper;
import viewControllers.libraryUI.LibraryView;
import viewControllers.libraryUI.LibraryViewController;
import viewControllers.libraryUI.SongView;
import netscan.status.checker.ApplicationConstants;
import netscan.status.checker.AvailableServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 2:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class SongSelectedAction extends AbstractAction{
    private SongWrapper songWrapper;
    private LibraryViewController libraryViewController;

    public SongSelectedAction(SongWrapper songWrapper, LibraryViewController libraryViewController) {
        super(songWrapper.getSongTitle());
        this.libraryViewController = libraryViewController;
        this.songWrapper = songWrapper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final LibraryView songView = new SongView(songWrapper, libraryViewController);
        libraryViewController.switchView(songView, true);
        final ConnectionModel connectionModel = libraryViewController.getConnectionController().getConnectionModel();

        int count = 0;
        int numConnections = connectionModel.getConnectionsAsAvailableServerList().size();
        long playTime = 0;
        for(AvailableServer connection : connectionModel.getConnectionsAsAvailableServerList()) {
            try {
                final Socket socket = new Socket(connection.getIpAddress(), ApplicationConstants.COMMAND_PORT_NUMBER);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                long startTime = System.currentTimeMillis();
                out.println(ComConstants.PLAY_SONG);
                input.readLine();
                try {
                    out.println(libraryViewController.toURI(songWrapper));
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
                input.readLine();
                //System.out.println("Start time: " + startTime);
                playTime = System.currentTimeMillis() + (numConnections-count)*(System.currentTimeMillis() - startTime);
                //System.out.println("Play time: " + playTime);
                out.println(playTime);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            count++;
        }
        try {
            if(playTime - System.currentTimeMillis() > 0) {
                Thread.sleep(playTime - System.currentTimeMillis());
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        libraryViewController.handleSongPlayback(songWrapper);
    }
}
