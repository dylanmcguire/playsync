package netscan.connectionsModel;

import netscan.status.checker.AvailableServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/26/14
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionModel {

    private Map<String, Connection> connectionMap;

    public ConnectionModel() {
        connectionMap = new HashMap<String, Connection>();
    }

    public void addConnection(Connection connection){
        connectionMap.put(connection.getIp(), connection);
    }

    public void removeConnection(String ipAddress) {
        connectionMap.remove(ipAddress);
    }

    public boolean isConnected(String ipAddress) {
        return connectionMap.containsKey(ipAddress);
    }


    public List<AvailableServer> getConnectionsAsAvailableServerList() {
        List<AvailableServer> availableServers = new ArrayList<AvailableServer>();
        for(String ipAddress : connectionMap.keySet()) {
            final Connection connection = connectionMap.get(ipAddress);
            availableServers.add(connection.getAvailableServer());
        }
        return availableServers;
    }

}
