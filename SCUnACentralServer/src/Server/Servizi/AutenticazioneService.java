package Server.Servizi;

import Domain.Utente;
import Server.Controller.ControllerFacade;
import Server.Controller.Observer;
import Server.ServerUtil.HibernateUtil;
import Server.ServerUtil.ServizioEsternoHolder;
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




            Session session = HibernateUtil.getSessionFactory().openSession();



            String id = (String) controller.getOggettiPersistenti().get(0);
            String Password = (String) controller.getOggettiPersistenti().get(1);


            Criteria cr = session.createCriteria(Utente.class);
            cr.add(Restrictions.eq("id", id));
            List results = cr.list();

            if(results.size() != 0){
                System.out.println("trovato l'utente");
            }






        }
    }

    @Override
    public void setNome(String nome) {

    }

    @Override
    public void setServiziEsterni(List<ServizioEsternoHolder> serviziEsterni) {

    }
}
