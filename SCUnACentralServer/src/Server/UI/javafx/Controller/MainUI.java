package Server.UI.javafx.Controller;

import Server.Settings.ParserXMLFile;
import Server.Settings.ServerParameter;
import Server.StartServer;
import Server.UI.UI_ServerFacade;
import Server.UI.javafx.Controller.Util.GraficalElement;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Febe on 26/12/2016.
 */
public class MainUI implements Initializable {



    public VBox LoggingVBox;
    public AnchorPane scrollPanelLogging;
    public Button chiudiServerButton;
    public VBox serviziAperti;
    public Label NomeServer;
    public ScrollPane Scroll;


    Thread th;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UI_ServerFacade.getSingletonInstance().setServizi(LoggingVBox);
        UI_ServerFacade.getSingletonInstance().setScrollPanel(scrollPanelLogging);
        UI_ServerFacade.getSingletonInstance().setScrollBar(Scroll);
        try {
            ParserXMLFile.getSingletonInstance().LeggiImpostazioni();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // aggiungo la lista dei servizi monitorati sul interfaccia
        List<String> serviziMonitorati = ServerParameter.getSingletonInstance().getServiziMonitorati();
        for(String servizioMonitorato : serviziMonitorati){
            serviziAperti.getChildren().add(GraficalElement.getSingletonInstance().getServizioMonitorato(servizioMonitorato));
        }
        // aggiungo Nome All'aplicazione
        NomeServer.setText(ServerParameter.getSingletonInstance().getNome());


    }

    public void apriServer(){
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                try{
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            chiudiServerButton.setVisible(true);
                        }
                    });
                    StartServer.getSingletonInstance().Start();
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
                chiudiServerButton.setVisible(false);
                UI_ServerFacade.getSingletonInstance().reportMessage("Server Chiuso");
            }
        });

        StartServer.getSingletonInstance().setStarted(false);

    }

}
