package Server.Servizi;

import Server.Controller.ControllerFacade;
import Server.Controller.Observer;
import Server.ServerUtil.ServizioEsternoHolder;
import Server.Storage.Storage;
import java.util.List;

/**
 * Created by agost on 10/01/2017.
 */
public class PaymentService  implements Observer{
    private String nome;
    private List<ServizioEsternoHolder> serviziEsterni;

    public void update(ControllerFacade controller) {
        if (controller.getFunzione().equals("Payment")) {

            Storage.getSingletonInstance().pay((String)controller.getOggettiPersistenti().get(0), (int)(controller.getOggettiPersistenti().get(1)));


        }

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setServiziEsterni(List<ServizioEsternoHolder> serviziEsterni) {
        this.serviziEsterni = serviziEsterni;
    }
}
