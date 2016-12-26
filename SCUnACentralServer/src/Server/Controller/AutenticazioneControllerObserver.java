package Server.Controller;

import Domain.Utente;
import Server.ServerUtil.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Febe on 24/12/2016.
 */
public class AutenticazioneControllerObserver implements Observer {
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
}
