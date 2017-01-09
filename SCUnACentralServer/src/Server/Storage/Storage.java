package Server.Storage;

import Domain.Utente;
import Server.ServerUtil.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by beniamino on 09/01/17.
 */
public class Storage {
    private static Storage ourInstance = new Storage();

    public static Storage getSingletonInstance() {
        return ourInstance;
    }

    private Storage() {
    }


    public void getAutenticazione (String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Utente.class);
        cr.add(Restrictions.eq("id", id));
        List results = cr.list();

        if(results.size() != 0){
            System.out.println("trovato l'utente");
        }
    }
}
