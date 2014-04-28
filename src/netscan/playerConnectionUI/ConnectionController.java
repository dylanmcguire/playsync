package netscan.playerConnectionUI;


import netscan.connectionsModel.ConnectionModel;
import netscan.status.checker.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/26/14
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionController implements StatusChecker{

    private NetScanner netScanner;
    private ConnectionView connectionView;
    private List<AvailableServer> availableServerList;
    private ConnectionModel connectionModel;

    public ConnectionController() {
        availableServerList = new ArrayList<AvailableServer>();
        connectionModel = new ConnectionModel();
        connectionView = new ConnectionView(connectionModel);
        try {
            //well not really this is actually the ipaddress of the host but because of the way the scanner is set up this will do just fine
            final String gatewayIP = ConnectionsUtil.getLocalAddress().getHostAddress();
            netScanner = new NetScanner(gatewayIP, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startScanForDevicesOnNetwork() {
        Thread runner = new Thread(netScanner);
        runner.start();
    }

    public JComponent getView() {
        return connectionView;
    }

    @Override
    public void handleConnection(BufferedReader inputStream, PrintWriter outputStream) throws IOException {
        final String greeting = inputStream.readLine();
        if (greeting.equals(ApplicationConstants.SCANNER_GREETING)) {
            outputStream.println("H");
            try{
                String info = inputStream.readLine();
                final String name = info.split(":")[0];
                final String ip = info.split(":")[1].replaceAll("/","");
                availableServerList.add(new AvailableServer(ip, name));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ConnectionModel getConnectionModel(){
        return connectionModel;
    }

    @Override
    public void update() {
        connectionView.setAvailableServerList(availableServerList);
        availableServerList.clear();
        connectionView.refresh();
    }

    public void setNetScannerLoggingLevel(LogLevel loggingLevel) {
        netScanner.setLoggingLevel(loggingLevel);
    }

}
