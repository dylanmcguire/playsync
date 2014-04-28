package viewControllers.player;

import Server.PlayerServer;
import audioPlayer.AudioPlayer;
import viewControllers.player.playerServers.ConnectionConfirmationServer;
import viewControllers.ViewController;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/28/14
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerViewController implements ViewController {


    private boolean connected = false;
    private String connectionName = "Awaiting Connection...";

    private JPanel view;

    public PlayerViewController() {
        AudioPlayer audioPlayer = new AudioPlayer();
        view = new JPanel();
        view.add(new JLabel(connectionName));
        Thread commandThread = new Thread(new PlayerServer(audioPlayer));
        commandThread.start();
        Thread connectConfirmThread = new Thread(new ConnectionConfirmationServer(this));
        connectConfirmThread.start();

    }

    @Override
    public JComponent getView() {
        return view;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setDisconnected() {
        this.connectionName = "Awaiting Connections...";
        connected = false;
        view.removeAll();
        view.add(new JLabel(connectionName));
        view.validate();
        view.repaint();
    }

    public void setConnected(String connectionName) {
        this.connectionName = connectionName;
        view.removeAll();
        view.add(new JLabel(connectionName));
        view.validate();
        view.repaint();
    }

}
