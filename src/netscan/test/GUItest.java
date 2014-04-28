package netscan.test;

import netscan.playerConnectionUI.ConnectionController;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: mcgui230
 * Date: 4/26/14
 * Time: 1:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class GUItest {

    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        final ConnectionController connectionController = new ConnectionController();
        frame.add(connectionController.getView());
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connectionController.startScanForDevicesOnNetwork();
    }

}
