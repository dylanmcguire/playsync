package netscan.playerConnectionUI.actions;

import netscan.connectionsModel.Connection;
import netscan.connectionsModel.ConnectionModel;
import netscan.playerConnectionUI.ConnectionsUtil;
import netscan.playerConnectionUI.Refreshable;
import netscan.status.checker.ApplicationConstants;
import netscan.status.checker.AvailableServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/26/14
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectToPlayerAction extends AbstractAction {

    private AvailableServer availableServer;
    private ConnectionModel connectionModel;
    private Refreshable refreshable;

    public ConnectToPlayerAction(AvailableServer availableServer, ConnectionModel connectionModel, Refreshable refreshable) {
        super("Connect");
        this.availableServer = availableServer;
        this.connectionModel = connectionModel;
        this.refreshable = refreshable;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        /*
        try {
            final Socket socket = new Socket(availableServer.getIpAddress(), ApplicationConstants.COMMAND_PORT_NUMBER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        final Socket socket;
        try {
            socket = new Socket(availableServer.getIpAddress(), ApplicationConstants.CONNECT_CONFIRM_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("connected");
            String message = input.readLine();
            if (message.equals("okay")) {
                connectionModel.addConnection(new Connection(availableServer));
                out.println(ConnectionsUtil.getLocalAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        refreshable.refresh();
    }



}
