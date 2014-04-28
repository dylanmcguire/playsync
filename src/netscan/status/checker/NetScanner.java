package netscan.status.checker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/25/14
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetScanner implements Runnable {
    private LogLevel logLevel = LogLevel.None;
    private static final int DEFAULT_DELAY_TIME = 1000; //1 second

    private String gatewayIP;
    private StatusChecker statusChecker;
    private boolean shouldRun = true;
    private int delayTime;


    public NetScanner(String gatewayIP, StatusChecker statusChecker) {
        this.gatewayIP = gatewayIP;
        this.statusChecker = statusChecker;
        delayTime = DEFAULT_DELAY_TIME;
    }


    @Override
    public void run() {
        final String ipBase = gatewayIP.substring(0,gatewayIP.lastIndexOf("."));
        System.out.println("Scanning from ip base " + ipBase);
        while (shouldRun) {
            for(int fourthQuadIPVal = 0; fourthQuadIPVal < 256; fourthQuadIPVal++) {
                try {
                    final Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ipBase+"."+fourthQuadIPVal, ApplicationConstants.SCANNER_PORT_NUMBER), 100);
                    //socket.setSoTimeout(1000);
                    final BufferedReader inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    final PrintWriter outputStream = new PrintWriter(socket.getOutputStream(), true);
                    statusChecker.handleConnection(inputStream, outputStream);
                } catch (IOException e) {
                    if(logLevel == LogLevel.Verbose) {
                        System.out.println("Tried address: " + ipBase+"."+fourthQuadIPVal);
                        e.printStackTrace();
                    }
                   //Do nothing. Machines on the network that are not running the application will refuse the connection, this is expected.
                }
            }
            statusChecker.update();
            try {
                Thread.sleep(delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public void stop() {
        shouldRun = false;
    }

    public void setLoggingLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
