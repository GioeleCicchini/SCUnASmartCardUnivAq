package Server;
import Server.Controller.*;
import Server.ServerUtil.HibernateUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartServer {


    private List<Observer> servizi = new ArrayList<>();

    private boolean started = false;
    private static StartServer singletonInstance = null;

    public static StartServer getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new StartServer();
        }
        return singletonInstance;
    }



    public void Start () throws IOException {


        ControllerFacade controllerFacade = new ControllerFacade();
        ConnectionListner entrataServer = new ConnectionListner();

        for(Observer servizio : servizi){
            controllerFacade.Attach(servizio);
        }


        entrataServer.setControllerFacade(controllerFacade);

        setStarted(true);
        entrataServer.StartServer();


    }


    public void addServizio(Observer servizio){
        servizi.add(servizio);
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
