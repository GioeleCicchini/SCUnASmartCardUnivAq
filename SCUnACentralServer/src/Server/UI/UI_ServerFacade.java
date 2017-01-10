package Server.UI;

import Server.UI.javafx.Controller.Util.GraficalElement;
import com.sun.javafx.scene.control.skin.ScrollPaneSkin;
import javafx.application.Platform;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


/**
 * Created by Febe on 26/12/2016.
 */
public class UI_ServerFacade implements UI_serverFacadeInterface {

    Integer Porta = null;

    public VBox Servizi;
    public AnchorPane scrollPanel;
    public ScrollPane scrollBar;


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
    public void reportMessage(final String messaggio) {

        Platform.runLater(new Runnable() {
            @Override public void run() {
                Servizi.getChildren().add(GraficalElement.getSingletonInstance().getReport(messaggio));
                scrollPanel.setPrefHeight(Servizi.getHeight()+80);
                scrollBar.setVvalue(1.0);
            }
        });


    }

    public void setServizi(VBox servizi) {
        Servizi = servizi;
    }
    public void setScrollPanel(AnchorPane scrollPanel) {
        this.scrollPanel = scrollPanel;
    }

    public void setScrollBar(ScrollPane scrollBar) {
        this.scrollBar = scrollBar;
    }
}
