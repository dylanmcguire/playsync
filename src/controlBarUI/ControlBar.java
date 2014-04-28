package controlBarUI;

import Server.PlayerServer;
import container.ContainerGUI;
import netscan.playerConnectionUI.ConnectionController;
import netscan.test.DummyServer;
import viewControllers.libraryUI.LibraryViewController;
import viewControllers.player.PlayerViewController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class ControlBar extends JPanel{



    public ControlBar(final ContainerGUI containerGUI, final ConnectionController connectionController, final PlayerServer playerServer) {

        final JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(containerGUI.getCurrentViewController() instanceof LibraryViewController) {
                    final LibraryViewController libraryViewController = (LibraryViewController) containerGUI.getCurrentViewController();
                    libraryViewController.showPreviousView();
                }
            }
        });


        final JButton remoteButton = new JButton("Remote");
        remoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionController.startScanForDevicesOnNetwork();
            }
        });

        final JButton playerButton = new JButton("player");
        playerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Thread serverThread = new Thread(playerServer);
                serverThread.start();

                Thread commandThread = new Thread(new DummyServer());
                commandThread.start();
                */
                containerGUI.setCurrentViewController(new PlayerViewController());
            }
        });

        add(backButton);
        add(remoteButton);
        add(playerButton);

    }



}
