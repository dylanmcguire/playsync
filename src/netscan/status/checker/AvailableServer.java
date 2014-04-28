package netscan.status.checker;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/25/14
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class AvailableServer {
    private String ipAddress;
    private String name;

    public AvailableServer(String ipAddress, String name) {
        this.ipAddress = ipAddress;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
