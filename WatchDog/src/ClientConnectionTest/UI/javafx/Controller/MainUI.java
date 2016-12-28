package ClientConnectionTest.UI.javafx.Controller;

import ClientConnectionTest.UI.javafx.Controller.Util.ServiziOsservati;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Febe on 26/12/2016.
 */
public class MainUI implements Initializable {

    public TextField nome;
    public TextField indirizzoIP;
    public TextField porta;
    public VBox Servizi;
    public AnchorPane scrollPanel;


    Thread th;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ServiziOsservati.getSingletonInstance().setServiziSpace(Servizi);

        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                while(true){
                    try {
                        ServiziOsservati.getSingletonInstance().PingToService();
                        Thread.sleep(1000);
                    } catch (IOException e) {
                        System.out.println("Errore di connessione");
                    }

                }
            }

        };

        th = new Thread(task);
        th.setDaemon(true);
        th.start();


    }


    public void Aggiungi(){

        if(nome.getText() != "" && indirizzoIP.getText() != "" && porta.getText() != ""){
            ServiziOsservati.getSingletonInstance().addNome(nome.getText());
            ServiziOsservati.getSingletonInstance().addIndirizzoIp(indirizzoIP.getText());
            ServiziOsservati.getSingletonInstance().addPorta(Integer.parseInt(porta.getText()));
            Servizi.getChildren().add(ServiziOsservati.getSingletonInstance().addLabel(nome.getText()));
            System.out.println(Servizi.getHeight());
            scrollPanel.setPrefHeight(Servizi.getHeight()+80);
        }else {
            System.out.println("parametri sbagliati");
        }


    }


}
