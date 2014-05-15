package viewControllers.libraryUI.actions;

import Server.ComConstants;
import netscan.connectionsModel.ConnectionModel;
import netscan.status.checker.ApplicationConstants;
import netscan.status.checker.AvailableServer;
import viewControllers.libraryUI.LibraryViewController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class PauseSongAction extends AbstractAction {

    private LibraryViewController viewController;
    public PauseSongAction(LibraryViewController viewController) {
        super("Pause");
        this.viewController = viewController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final ConnectionModel  connectionModel = viewController.getConnectionController().getConnectionModel();

        for(AvailableServer availableServer : connectionModel.getConnectionsAsAvailableServerList()) {
            try {
                final Socket socket = new Socket(availableServer.getIpAddress(), ApplicationConstants.COMMAND_PORT_NUMBER);
                final PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println(ComConstants.PAUSE_SONG);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        viewController.handleSongPause();
    }
}
