package Server.UI.javafx.Controller;

import Server.Settings.ReadXMLFile;
import Server.StartServer;
import Server.UI.UI_ServerFacade;
import Server.UI.javafx.Controller.Util.GraficalElement;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;
import java.net.BindException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Febe on 26/12/2016.
 */
public class MainUI implements Initializable {


    public TextField porta;
    public VBox Servizi;
    public AnchorPane scrollPanel;
    public Button chiudiServerButton;


    Thread th;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UI_ServerFacade.getSingletonInstance().setServizi(Servizi);
        UI_ServerFacade.getSingletonInstance().setScrollPanel(scrollPanel);



    }

    public void apriServer(){
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                try{
                   // UI_ServerFacade.getSingletonInstance().setPorta(Integer.parseInt(porta.getText()));
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            porta.setEditable(false);
                            porta.setDisable(true);
                            chiudiServerButton.setVisible(true);
                        }
                    });
                    ReadXMLFile.getSingletonInstance().LeggiImpostazioni();
                }catch (NumberFormatException e2){
                    UI_ServerFacade.getSingletonInstance().reportMessage("Errore porta");
                }
                return null;
            }

        };

        th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    public void chiudiServer(){

        Platform.runLater(new Runnable() {
            @Override public void run() {
                porta.setEditable(true);
                porta.setDisable(false);
                chiudiServerButton.setVisible(false);
                UI_ServerFacade.getSingletonInstance().reportMessage("Server Chiuso");
            }
        });

        StartServer.getSingletonInstance().setStarted(false);

    }

}
