package ClientConnectionTest.UI.javafx.Controller.Util;

import ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService;
import ClientConnectionTest.UI.ListaServizi;
import Util.DTO;
import Util.DTOMaker;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.io.IOException;
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

    HBox serviziSpace = null;

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
                                label.get(finalI).setText(label.get(finalI).getText());

                    }
                });

                    }
                } catch (IOException e) {

                    int finalI = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            label.get(finalI).setText("Non in funzione");
                        }
                    });


                }
            }

        }
    }


    public void setServiziSpace(HBox serviziSpace) {
        this.serviziSpace = serviziSpace;
    }
}
