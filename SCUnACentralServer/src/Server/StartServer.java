package Server;
import Server.Controller.*;

import java.io.IOException;

public class StartServer {



    private boolean started = false;
    private static StartServer singletonInstance = null;

    public static StartServer getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new StartServer();
        }
        return singletonInstance;
    }



    public void Start () throws IOException {

        PingServiceControllerObserver pingServiceControllerObserver = new PingServiceControllerObserver();
        AutenticazioneControllerObserver autenticazioneControllerObserver = new AutenticazioneControllerObserver();



        ControllerFacade controllerFacade = new ControllerFacade();
        ConnectionListner entrataServer = new ConnectionListner();

        controllerFacade.Attach(pingServiceControllerObserver);
        controllerFacade.Attach(autenticazioneControllerObserver);


        entrataServer.setControllerFacade(controllerFacade);

        setStarted(true);
        entrataServer.StartServer();

    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
