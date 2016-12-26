package Server.UI;

import Server.StartServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Febe on 26/12/2016.
 */
public class ServerGUI {
    private JPanel panel1;
    private JButton startServerButton;
    private JTextArea RegistroTextArea;
    private JTextField PortaTextBox;


    private static ServerGUI singletonInstance = null;

    public static ServerGUI getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ServerGUI();
        }
        return singletonInstance;
    }



    public ServerGUI() {
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Thread(new Runnable() {
                    public void run() {
                        try {

                            try{
                                UI_ServerFacade.getSingletonInstance().setPorta(Integer.parseInt(PortaTextBox.getText()));
                            }catch (NumberFormatException e2){
                                RegistroTextArea.append("ERRORE, Porta compresa tra 1024-10000 \n");
                            }

                            try {
                                StartServer.getSingletonInstance().Start();
                            } catch (NullPointerException e){
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        RegistroTextArea.append("ERRORE, Server già aperto o numero di porta sbagliato \n");
                                    }
                                });
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    }).start();
            }
        });
    }


    public JTextArea getRegistroTextArea() {
        return RegistroTextArea;
    }

    public void setRegistroTextArea(JTextArea registroTextArea) {
        this.RegistroTextArea = registroTextArea;
    }

    public JTextField getPortaTextBox() {
        return PortaTextBox;
    }

    public void setPortaTextBox(JTextField portaTextBox) {
        this.PortaTextBox = portaTextBox;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SCUnA Central Server");
        frame.setContentPane(new ServerGUI().getSingletonInstance().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);
    }
}
