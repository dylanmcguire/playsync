package netscan.playerConnectionUI.actions;

import netscan.connectionsModel.Connection;
import netscan.connectionsModel.ConnectionModel;
import netscan.playerConnectionUI.ConnectionView;
import netscan.playerConnectionUI.Refreshable;
import netscan.status.checker.AvailableServer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 5/13/14
 * Time: 6:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddNewConnectionToListActions extends AbstractAction{

    private ConnectionModel connectionModel;
    private Refreshable refreshable;

    public AddNewConnectionToListActions(ConnectionModel connectionModel, Refreshable refreshable) {
        super("+");
        this.connectionModel = connectionModel;
        this.refreshable = refreshable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String ipAddress = JOptionPane.showInputDialog("Enter the IP address of the machine you wish to connect to.");
        final AvailableServer availableServer = new AvailableServer(ipAddress, ipAddress);
        final ConnectToPlayerAction connectToPlayerAction = new ConnectToPlayerAction(availableServer, connectionModel, refreshable);
        connectToPlayerAction.actionPerformed(e);
    }
}
