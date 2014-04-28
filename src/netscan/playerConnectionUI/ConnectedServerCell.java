package netscan.playerConnectionUI;

import netscan.connectionsModel.ConnectionModel;
import netscan.playerConnectionUI.actions.DisconnectFromPlayerAction;
import netscan.status.checker.AvailableServer;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/26/14
 * Time: 2:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectedServerCell extends JPanel {

    private AvailableServer availableServer;
    private ConnectionModel connectionModel;

    public ConnectedServerCell(AvailableServer availableServer, ConnectionModel connectionModel, Refreshable refreshable) {
        setPreferredSize(new Dimension(200,50));
        this.availableServer = availableServer;
        this.connectionModel = connectionModel;
        add(new JLabel(availableServer.getIpAddress()));
        add(new JButton(new DisconnectFromPlayerAction(availableServer, connectionModel, refreshable)));
    }






}
