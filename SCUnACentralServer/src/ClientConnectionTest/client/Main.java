package ClientConnectionTest.client;

import ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService;
import Domain.Utente;
import Util.DTO;
import Util.DTOMaker;

import java.io.IOException;

/**
 * Created by Febe on 24/12/2016.
 */
public class Main {

    public static void main(String [ ] args) throws IOException {

        System.out.println("Richiesta Ping");

        ConcreteRemoteService service = ConcreteRemoteService.getSingletonInstance();
        DTOMaker dtoMaker = DTOMaker.getSingletonInstance();

        DTO risp = (DTO) service.RichiediAlServer(dtoMaker.getPingDTO());

        DTO risp2 = (DTO) service.RichiediAlServer(dtoMaker.getProva());





        if (risp.getFunzione().equals("Ping")) {
            System.out.println("Ping Ricevuto Con successo");

        }



    }




}
