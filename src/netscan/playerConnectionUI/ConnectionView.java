package netscan.playerConnectionUI;

import netscan.connectionsModel.ConnectionModel;
import netscan.playerConnectionUI.actions.AddNewConnectionToListActions;
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
    private JPanel connectionsPanel;
    private ConnectionModel connectionModel;

    public ConnectionView(ConnectionModel connectionModel) {
        this.connectionModel = connectionModel;
        setLayout(new BorderLayout());
        connectionsPanel = new JPanel();
        connectionsPanel.setPreferredSize(new Dimension(200, 200));
        connectionsPanel.setLayout(new GridLayout(1,20));
        final JPanel controllerPanel = new JPanel();
        controllerPanel.add(new JLabel("Available Stream Players"));
        final JButton jbutton = new JButton(new AddNewConnectionToListActions(connectionModel, this));
        controllerPanel.add(jbutton);
        add(controllerPanel, BorderLayout.NORTH);
        add(connectionsPanel, BorderLayout.CENTER);
    }


    public void setAvailableServerList(List<AvailableServer> availableServerList) {
        this.availableServerList = new ArrayList<AvailableServer>(availableServerList);
    }

    @Override
    public void refresh() {
        connectionsPanel.removeAll();
        for(AvailableServer availableServer : connectionModel.getConnectionsAsAvailableServerList()) {
            connectionsPanel.add(new ConnectedServerCell(availableServer, connectionModel, this));
        }
        for(AvailableServer availableServer : availableServerList) {
            if(!connectionModel.isConnected(availableServer.getIpAddress())) {
                connectionsPanel.add(new AvailableServerCell(availableServer, connectionModel, this));
            }
        }
        validate();
        repaint();
    }

}
