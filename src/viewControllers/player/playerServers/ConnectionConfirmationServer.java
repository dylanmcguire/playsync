package viewControllers.player.playerServers;

import netscan.status.checker.ApplicationConstants;
import viewControllers.player.PlayerViewController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/28/14
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionConfirmationServer implements Runnable{

    private PlayerViewController playerViewController;

    public ConnectionConfirmationServer(PlayerViewController playerViewController) {
        this.playerViewController = playerViewController;
    }

    @Override
    public void run() {
        ServerSocket listener = null;
        BroadcastingServer broadcastingServer = new BroadcastingServer();
        new Thread(broadcastingServer).start();
        try {
            listener = new ServerSocket(ApplicationConstants.CONNECT_CONFIRM_PORT);

            try {
                System.out.println("Awaiting connection confirmations...");
                while (true) {
                    Socket socket = listener.accept();

                    PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                    try {
                        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String message = inputStream.readLine();
                        if (message.equals("connected")) {
                            if(!playerViewController.isConnected()) {
                                out.println("okay");
                                final String name = inputStream.readLine();
                                playerViewController.setConnected(name);
                                broadcastingServer.stopBroadcast();
                            }
                            else {
                                out.println("bad");
                            }
                        }
                        else if(message.equals("disconnected")) {
                            playerViewController.setDisconnected();
                            broadcastingServer = new BroadcastingServer();
                            new Thread(broadcastingServer).start();
                        }
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
