package Util;

import ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService;
import ClientConnectionTest.UI.ListaServizi;

import java.util.ArrayList;

/**
 * Created by Febe on 26/12/2016.
 */
public class ServiziOsservati {

    ArrayList<String> indirizziIP = new ArrayList<>();
    ArrayList<Integer> porte = new ArrayList<>();


    private static ServiziOsservati singletonInstance = null;


    public static ServiziOsservati getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ServiziOsservati();
        }
        return singletonInstance;
    }



    public ServiziOsservati() {
        indirizziIP.add("localhost");
        porte.add(6789);

    }


    public void addIndirizzoIp(String indirizzo){
        this.indirizziIP.add(indirizzo);
        ListaServizi.getSingletonInstance().AggiungiElemento(indirizzo);
    }
    public void addPorta(Integer porta){
        this.porte.add(porta);
    }

    public ArrayList<String> getIndirizziIP() {
        return indirizziIP;
    }


    public ArrayList<Integer> getPorte() {
        return porte;
    }




}
