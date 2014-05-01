package container;

import Server.PlayerServer;
import audioPlayer.AudioPlayer;
import controlBarUI.ControlBar;
import library.Library;
import viewControllers.ViewController;
import viewControllers.libraryUI.LibraryViewController;
import netscan.playerConnectionUI.ConnectionController;
import netscan.status.checker.LogLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: dylan
 * Date: 4/27/14
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContainerGUI extends JFrame{

    private ViewController currentViewController;

    public static void main(String[] args) {
        new ContainerGUI();
    }

    public ContainerGUI() {

        JPanel logginPanel = new JPanel();
        logginPanel.setPreferredSize(new Dimension(400, 75));
        logginPanel.add(new JLabel("Username:"));
        final JTextField userNameField = new JTextField();
        userNameField.setPreferredSize(new Dimension(100, 20));
        logginPanel.add(userNameField);
        logginPanel.add(new JLabel("Password"));
        final JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(100, 20));
        logginPanel.add(passwordField);
        JOptionPane.showConfirmDialog(this, logginPanel,"Login to Google Music", JOptionPane.OK_CANCEL_OPTION);
        startUp(userNameField.getText(), passwordField.getText());
    }


    public void startUp(String username, String pass) {
        final Library library = new Library(username, pass);
        final ConnectionController connectionController = new ConnectionController();
        connectionController.setNetScannerLoggingLevel(LogLevel.None);
        final AudioPlayer audioPlayer = new AudioPlayer();
        final PlayerServer playerServer = new PlayerServer(audioPlayer);
        final ControlBar controlBar = new ControlBar(this, connectionController, playerServer);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        currentViewController = new LibraryViewController(library, connectionController, audioPlayer);
        add(currentViewController.getView(), BorderLayout.CENTER);
        add(connectionController.getView(), BorderLayout.EAST);
        add(controlBar, BorderLayout.NORTH);
        validate();
        pack();
        setVisible(true);
        repaint();
    }

    public ViewController getCurrentViewController() {
        return currentViewController;
    }

    public void setCurrentViewController(ViewController viewController){
        remove(currentViewController.getView());
        currentViewController = viewController;
        add(currentViewController.getView());
        pack();
    }

    private class LoginPanel extends JPanel{
        public LoginPanel() {
            setPreferredSize(new Dimension(400, 75));
            add(new JLabel("Username:"));
            final JTextField userNameField = new JTextField();
            userNameField.setPreferredSize(new Dimension(100, 20));
            add(userNameField);
            add(new JLabel("Password"));
            final JPasswordField passwordField = new JPasswordField();
            passwordField.setPreferredSize(new Dimension(100, 20));
            add(passwordField);
            final JButton loginButton = new JButton("Login");
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startUp(userNameField.getText(), passwordField.getText());
                }
            });
            add(loginButton);
        }
    }

}

