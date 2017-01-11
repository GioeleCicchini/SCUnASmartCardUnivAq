package Server.Storage;

import Domain.Pagamento;
import Domain.Stanza;
import Domain.Utente;
import Server.ServerUtil.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.UUID;

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


    public boolean getAutenticazioneStanza(String idUtente,String idStanza){
        boolean Autorizzazione = false;
        Utente utente = new Utente();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Utente.class);
        cr.add(Restrictions.eq("id", idUtente));
        List results = cr.list();

        if(results.size() != 0){
            System.out.println("trovato l'utente");
            utente = (Utente) results.get(0);
        }


        Criteria cr2 = session.createCriteria(Stanza.class);
        cr2.add(Restrictions.eq("idStanza", idStanza));
        List resultsStanza = cr2.list();

        if(resultsStanza.size() != 0){
            if(utente.getLivelloAutorizzazione() >= ((Stanza) resultsStanza.get(0)).getLivelloAutorizzazione()){
                Autorizzazione = true;
                System.out.println("Autorizzaro");
            }
        }

        return Autorizzazione;

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

    public void pay(String idUtente, int amount) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Utente.class);
        cr.add(Restrictions.eq("id", idUtente));
        List results = cr.list();
        if(results.size() != 0){
            Utente u = (Utente) results.get(0);
            int currcredito = u.getCredito();
            Pagamento p = new Pagamento();
            p.setIdPagamento(UUID.randomUUID().toString());
            p.setAmount(amount);
            u.aggiungiPagamento(p);
            if(currcredito >= amount) {
                u.setCredito(currcredito - amount);
            } else
            {
                System.err.println("Non c'è credito per questo utente");
            }
            try {
                session.beginTransaction();
                session.update(u);
                session.getTransaction().commit();
            } catch(HibernateException e) {
                e.printStackTrace();
            }
        }
    }
}
