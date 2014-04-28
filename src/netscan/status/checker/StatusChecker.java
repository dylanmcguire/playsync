package netscan.status.checker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/25/14
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StatusChecker {

    //handle the connection with the server
    public void handleConnection(BufferedReader inputStream, PrintWriter outputStream) throws IOException;

    //update necessary components
    public void update();
}
