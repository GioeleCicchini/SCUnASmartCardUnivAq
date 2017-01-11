package Server.Servizi;

import Server.Controller.ControllerFacade;
import Server.Controller.Observer;
import Server.ServerUtil.DTOMaker;
import Server.ServerUtil.RispostaMaker;
import Server.ServerUtil.ServizioEsternoHolder;
import Server.Storage.Storage;
import org.dto.DTO;

import java.util.List;

/**
 * Created by Febe on 11/01/2017.
 */
public class AutenticazioneStanzaService implements Observer{

    private String nome;
    private List<ServizioEsternoHolder> serviziEsterni;



    @Override
    public void update(ControllerFacade controller) {

        if(controller.getFunzione().equals("AutenticazioneStanza")){

            boolean Autorizzazione = false;
            DTO rispostaDTO = null;

            String idUtente = (String) controller.getOggettiPersistenti().get(0);
            String idStanza = (String) controller.getOggettiPersistenti().get(1);
            Autorizzazione= Storage.getSingletonInstance().getAutenticazioneStanza(idUtente,idStanza);

            rispostaDTO = DTOMaker.getSingletonInstance().getRispostaAutenticazioneStanza(Autorizzazione);

            RispostaMaker.getSingletonInstance().ImmettiRisposta(rispostaDTO);

        }
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setServiziEsterni(List<ServizioEsternoHolder> serviziEsterni) {
        this.serviziEsterni = serviziEsterni;

    }
}
