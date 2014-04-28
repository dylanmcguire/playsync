package netscan.connectionsModel;

import netscan.status.checker.AvailableServer;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/26/14
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Connection {

    private AvailableServer availableServer;

    public Connection(AvailableServer availableServer) {
        this.availableServer = availableServer;
    }

    public String getName() {
        return availableServer.getName();
    }

    public String getIp() {
        return availableServer.getIpAddress();
    }

    public AvailableServer getAvailableServer() {
        return availableServer;
    }

}
