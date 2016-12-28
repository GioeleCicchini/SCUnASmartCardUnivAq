package Server.Servizi;

import Server.Controller.ControllerFacade;
import Server.Controller.Observer;
import Server.ServerUtil.RispostaMaker;
import Server.Settings.servizioEsterno;
import Util.DTO;
import Util.DTOMaker;

import java.util.List;

/**
 * Created by Febe on 24/12/2016.
 */
public class PingService implements Observer {


    private List<servizioEsterno> serviziEsterni;
    private String nomeServizio;



    @Override
    public void update(ControllerFacade controller) {

        if (controller.getFunzione().equals("Ping")) {

            DTO rispostaDTO = null;
            rispostaDTO = DTOMaker.getSingletonInstance().getPingDTO();
            RispostaMaker.getSingletonInstance().ImmettiRisposta(rispostaDTO);

            System.out.println("rispondo al Ping");

        }
    }

    @Override
    public void setNome(String nome) {
        this.nomeServizio = nomeServizio;
    }

    @Override
    public void setServiziEsterni(List<servizioEsterno> serviziEsterni) {
        this.serviziEsterni = serviziEsterni;
    }

}
