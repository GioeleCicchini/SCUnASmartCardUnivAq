package ClientConnectionTest.client;

import ClientConnectionTest.Fondation.TecnicalService.ConcreteRemoteService;
import Server.ServerUtil.DTOMaker;
import org.dto.DTO;


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

        if (risp.getFunzione().equals("Ping")) {
            System.out.println("Ping Ricevuto Con successo");

        }

        DTO risposta= (DTO) service.RichiediAlServer(dtoMaker.getPayments("gioele",10));


        DTO rispostaAutorizzazioneStanza = (DTO) service.RichiediAlServer(dtoMaker.getAutenticazioneStanza("gioele","stanza1"));

        if (rispostaAutorizzazioneStanza.getFunzione().equals("RispostaAutenticazioneStanza")) {
            if((boolean)rispostaAutorizzazioneStanza.getOggettiTrasferimento().get(0)){
                System.out.println("Autenticazione Riuscita");
            }else{
                System.out.println("Non Hai i privilegi");
            }
        }

        DTO rispostaAutorizzazioneStanza2 = (DTO) service.RichiediAlServer(dtoMaker.getAutenticazioneStanza("gioele","stanza2"));

        if (rispostaAutorizzazioneStanza2.getFunzione().equals("RispostaAutenticazioneStanza")) {
            if((boolean)rispostaAutorizzazioneStanza2.getOggettiTrasferimento().get(0)){
                System.out.println("Autenticazione Riuscita");
            }else{
                System.out.println("Non Hai i privilegi");
            }
        }






    }




}
