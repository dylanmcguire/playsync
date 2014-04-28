package netscan.playerConnectionUI;

import netscan.connectionsModel.ConnectionModel;
import netscan.status.checker.AvailableServer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/26/14
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionView extends JPanel implements Refreshable{

    private List<AvailableServer> availableServerList = new ArrayList<AvailableServer>();

    private ConnectionModel connectionModel;

    public ConnectionView(ConnectionModel connectionModel) {
        this.connectionModel = connectionModel;
        setPreferredSize(new Dimension(200,200));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Available Stream Players"));
    }


    public void setAvailableServerList(List<AvailableServer> availableServerList) {
        this.availableServerList = new ArrayList<AvailableServer>(availableServerList);
    }

    @Override
    public void refresh() {
        removeAll();
        for(AvailableServer availableServer : connectionModel.getConnectionsAsAvailableServerList()) {
            add(new ConnectedServerCell(availableServer, connectionModel, this));
        }
        for(AvailableServer availableServer : availableServerList) {
            if(!connectionModel.isConnected(availableServer.getIpAddress())) {
                add(new AvailableServerCell(availableServer, connectionModel, this));
            }
        }
        validate();
        repaint();
    }

}
