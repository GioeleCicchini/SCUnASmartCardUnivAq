package Server.Settings;

import Server.StartServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Febe on 28/12/2016.
 */
public class ServerParameter {



    private static ServerParameter  singletonInstance = null;

    public static ServerParameter  getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ServerParameter ();
        }
        return singletonInstance;
    }



    private String nome;
    private Integer porta;
    private List<String> serviziMonitorati = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    public void AddServizioMonitorato(String servizio){
        this.serviziMonitorati.add(servizio);
    }

    public List<String> getServiziMonitorati() {
        return serviziMonitorati;
    }
}
