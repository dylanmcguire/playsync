package netscan.test;

import netscan.status.checker.ApplicationConstants;
import netscan.status.checker.AvailableServer;
import netscan.status.checker.StatusChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/25/14
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class testStatusChecker implements StatusChecker {

    private List<AvailableServer> availableServerList;

    public testStatusChecker() {
        availableServerList = new ArrayList<AvailableServer>();
    }


    @Override
    public void handleConnection(BufferedReader inputStream, PrintWriter outputStream) throws IOException {
        final String greeting = inputStream.readLine();
        if (greeting.equals(ApplicationConstants.SCANNER_GREETING)) {
            outputStream.println("H");
            try{
            String info = inputStream.readLine();
            final String name = info.split(":")[0];
            final String ip = info.split(":")[1].replace("/","");

            availableServerList.add(new AvailableServer(ip, name));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
       for (AvailableServer server : availableServerList) {
           System.out.println(server.getName() + ": " + server.getIpAddress());
       }
       availableServerList.clear();
    }
}
