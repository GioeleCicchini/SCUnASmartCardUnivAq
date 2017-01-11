package Domain;

import java.util.List;

/**
 * Created by Febe on 24/12/2016.
 */
public class Utente {

    private String id;
    private String Password;
    private List<Pagamento> pagamenti;
    private int livelloAutorizzazione;
    private int credito;

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public List<Pagamento> getPagamenti() {
        return pagamenti;
    }

    public Utente() {
    }

    public void aggiungiPagamento(Pagamento p) {
        pagamenti.add(p);
    }

    public void setPagamenti(List<Pagamento> pagamenti) {
        this.pagamenti = pagamenti;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getLivelloAutorizzazione() {
        return livelloAutorizzazione;
    }

    public void setLivelloAutorizzazione(int livelloAutorizzazione) {
        this.livelloAutorizzazione = livelloAutorizzazione;
    }
}
