package Server.UI;

import Server.UI.javafx.Controller.Util.GraficalElement;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created by Febe on 26/12/2016.
 */
public class UI_ServerFacade implements UI_serverFacadeInterface {

    Integer Porta = null;

    public VBox Servizi;
    public AnchorPane scrollPanel;


    private static UI_ServerFacade singletonInstance = null;

    public static UI_ServerFacade getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new UI_ServerFacade();
        }
        return singletonInstance;
    }


    @Override
    public void setPorta(int Porta) {
        this.Porta = Porta;
    }

    @Override
    public Integer getPorta() {
        return this.Porta;
    }

    @Override
    public void reportMessage(String messaggio) {

        Platform.runLater(new Runnable() {
            @Override public void run() {
                Servizi.getChildren().add(GraficalElement.getSingletonInstance().getReport(messaggio));
                scrollPanel.setPrefHeight(Servizi.getHeight()+80);
            }
        });


    }


    public void setServizi(VBox servizi) {
        Servizi = servizi;
    }

    public void setScrollPanel(AnchorPane scrollPanel) {
        this.scrollPanel = scrollPanel;
    }
}
