package ClientConnectionTest.UI.javafx.Controller.Util;

import ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService;
import ClientConnectionTest.UI.ListaServizi;
import Util.DTO;
import Util.DTOMaker;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javax.swing.*;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by Febe on 26/12/2016.
 */
public class ServiziOsservati {

    ArrayList<String> nomi = new ArrayList<>();
    ArrayList<String> indirizziIP = new ArrayList<>();
    ArrayList<Integer> porte = new ArrayList<>();
    ArrayList<Label> label = new ArrayList<>();

    VBox serviziSpace = null;

    private static ServiziOsservati singletonInstance = null;


    public static ServiziOsservati getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ServiziOsservati();
        }
        return singletonInstance;
    }



    public ServiziOsservati() {
    }


    public void addIndirizzoIp(String indirizzo){
        this.indirizziIP.add(indirizzo);
        ListaServizi.getSingletonInstance().AggiungiElemento(indirizzo);
    }
    public void addPorta(Integer porta){
        this.porte.add(porta);
    }

    public void addNome(String nome){
        this.nomi.add(nome);
    }

    public ArrayList<String> getIndirizziIP() {
        return indirizziIP;
    }


    public ArrayList<Integer> getPorte() {
        return porte;
    }


    public Label addLabel(String nome){
        Label label = new Label();
        label.setStyle("-fx-border-color:red; -fx-background-color: red; -fx-font-size: 20px");
        label.setMaxWidth(200);
        label.setMaxHeight(50);
        label.setPadding(new Insets(10, 0, 10, 20));
        label.setText(nome);

        this.label.add(label);

        return label;

    }


    public void PingToService() throws IOException{

        if(indirizziIP.size() != 0) {
            for (int i = 0; i < indirizziIP.size(); i++) {

                try {
                    DTO risp = ConcreteRemoteService.getSingletonInstance().RichiediAlServer(DTOMaker.getSingletonInstance().getPingDTO(), indirizziIP.get(i), porte.get(i));
                    if (risp.getFunzione().equals("Ping")) {
                        int finalI = i;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                label.get(finalI).setStyle("-fx-border-color:green; -fx-background-color: forestgreen; -fx-font-size: 20px");
                    }
                });
                    }
                }
                catch (IOException e) {
                    System.out.println("Nessuno In ascolto");
                    int finalI = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            label.get(finalI).setStyle("-fx-border-color:darkred; -fx-background-color: red; -fx-font-size: 20px");
                        }
                    });


                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public void setServiziSpace(VBox serviziSpace) {
        this.serviziSpace = serviziSpace;
    }
}
