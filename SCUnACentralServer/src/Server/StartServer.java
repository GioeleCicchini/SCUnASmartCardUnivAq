package Server;
import Server.Controller.*;
import Server.ServerUtil.RispostaMaker;
import Server.UI.ServerGUI;

import java.io.IOException;

public class StartServer {


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


        entrataServer.StartServer();



    }
}
