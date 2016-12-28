package Server.Settings;

import Server.StartServer;

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



    String nome;
    Integer porta;

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
}
