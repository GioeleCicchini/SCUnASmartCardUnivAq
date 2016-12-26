package ClientConnectionTest.UI;



import Util.ServiziOsservati;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Febe on 26/12/2016.
 */
public class WatchDogUI {
    private JTextField indirizzoIP;
    private JTextField Porta;
    private JButton aggiungiButton;
    private JPanel MainPanel;
    public JPanel ServiziAperti = new JPanel();



    private static WatchDogUI singletonInstance = null;



    public static WatchDogUI getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new WatchDogUI();
        }
        return singletonInstance;
    }

    public WatchDogUI() {
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            ServiziOsservati.getSingletonInstance().addIndirizzoIp(indirizzoIP.getText());
            }
        });
    }





    public static void main(String[] args) {
        JFrame frame = new JFrame("WatchDog");
        frame.setContentPane(new WatchDogUI().getSingletonInstance().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ListaServizi.getSingletonInstance().inizializzaLista();

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

}