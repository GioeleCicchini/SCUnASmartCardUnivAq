package Server;

import Domain.Utente;
import Server.Controller.*;
import Server.ServerUtil.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

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
