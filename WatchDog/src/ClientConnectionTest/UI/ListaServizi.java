package ClientConnectionTest.UI;

import Util.ServiziOsservati;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Febe on 26/12/2016.
 */
public class ListaServizi {


    private static ListaServizi singletonInstance = null;
    private JPanel lista = null;

    public static ListaServizi getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ListaServizi();
        }
        return singletonInstance;
    }

    public void inizializzaLista(){

        setLista(WatchDogUI.getSingletonInstance().ServiziAperti);

        this.lista.add(new JLabel("ciao"));

    }

    public void setLista(JPanel lista) {
        this.lista = lista;
    }

    public void AggiungiElemento(String elemento){

    }



}
