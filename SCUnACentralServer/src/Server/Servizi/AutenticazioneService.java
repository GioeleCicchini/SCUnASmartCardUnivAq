package Server.Servizi;

import Domain.Utente;
import Server.Controller.ControllerFacade;
import Server.Controller.Observer;
import Server.ServerUtil.HibernateUtil;
import Server.ServerUtil.ServizioEsternoHolder;
import Server.Storage.Storage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


import java.util.List;

/**
 * Created by Febe on 24/12/2016.
 */
public class AutenticazioneService implements Observer {
    @Override
    public void update(ControllerFacade controller) {

        if (controller.getFunzione().equals("Autenticazione")) {

            String id = (String) controller.getOggettiPersistenti().get(0);
            String Password = (String) controller.getOggettiPersistenti().get(1);

            Storage.getSingletonInstance().getAutenticazione(id);

        }
    }

    @Override
    public void setNome(String nome) {

    }

    @Override
    public void setServiziEsterni(List<ServizioEsternoHolder> serviziEsterni) {

    }
}
