package container;

import Server.PlayerServer;
import audioPlayer.AudioPlayer;
import controlBarUI.ControlBar;
import library.Library;
import viewControllers.ViewController;
import viewControllers.libraryUI.LibraryViewController;
import netscan.playerConnectionUI.ConnectionController;
import netscan.status.checker.LogLevel;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContainerGUI extends JFrame{

    private ViewController currentViewController;

    public static void main(String[] args) {
        new ContainerGUI();
    }

    public ContainerGUI() {
        final Library library = new Library();
        final ConnectionController connectionController = new ConnectionController();
        connectionController.setNetScannerLoggingLevel(LogLevel.None);
        final AudioPlayer audioPlayer = new AudioPlayer();
        final PlayerServer playerServer = new PlayerServer(audioPlayer);
        final ControlBar controlBar = new ControlBar(this, connectionController, playerServer);
        currentViewController = new LibraryViewController(library, connectionController, audioPlayer);
        add(currentViewController.getView(), BorderLayout.CENTER);
        add(connectionController.getView(), BorderLayout.EAST);
        add(controlBar, BorderLayout.NORTH);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public ViewController getCurrentViewController() {
        return currentViewController;
    }

    public void setCurrentViewController(ViewController viewController){
        remove(currentViewController.getView());
        currentViewController = viewController;
        add(currentViewController.getView());
        pack();
    }

}
