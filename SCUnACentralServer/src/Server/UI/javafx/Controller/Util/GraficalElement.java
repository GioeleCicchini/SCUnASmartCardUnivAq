package Server.UI.javafx.Controller.Util;

import Server.UI.UI_ServerFacade;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

/**
 * Created by Febe on 27/12/2016.
 */
public class GraficalElement {

    private static GraficalElement singletonInstance = null;

    public static GraficalElement getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new GraficalElement();
        }
        return singletonInstance;
    }





    public Label getReport(String report){

        Label label = getLabel();
        label.setText(report);

        return label;

    }

    public Label getErrorePorta(){

        Label label = getLabel();
        label.setText("ERRORE, Porta compresa tra 1024-10000 ");
        return label;

    }



    private Label getLabel(){
        Label label = new Label();
        label.setMaxWidth(200);
        label.setMaxHeight(50);
        label.setPadding(new Insets(3, 0, 3, 20));

        return label;
    }





}
