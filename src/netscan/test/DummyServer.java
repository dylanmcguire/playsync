package netscan.test;

import netscan.playerConnectionUI.ConnectionsUtil;
import netscan.status.checker.ApplicationConstants;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/25/14
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class DummyServer implements Runnable{

    public void run (){
        ServerSocket listener = null;
        try {
            listener = new ServerSocket(ApplicationConstants.SCANNER_PORT_NUMBER);

        try {
            System.out.println("Awaiting connections...");
            while (true) {
                Socket socket = listener.accept();
                System.out.println("Got One!!");
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                try {
                    out.println(ApplicationConstants.SCANNER_GREETING);
                    System.out.println("Sent Greeting");
                    socket.getInputStream().read();
                    System.out.println("Got reply");
                    out.println("My Name:" + ConnectionsUtil.getLocalAddress());
                    System.out.print("Sent info");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    out.close();
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
        }
        catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}


