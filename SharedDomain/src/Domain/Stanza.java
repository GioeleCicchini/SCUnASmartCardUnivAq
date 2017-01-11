package Domain;

/**
 * Created by Febe on 11/01/2017.
 */
public class Stanza {

    private String idStanza;
    private int livelloAutorizzazione;


    public Stanza() {
    }

    public String getIdStanza() {
        return idStanza;
    }

    public void setIdStanza(String idStanza) {
        this.idStanza = idStanza;
    }

    public int getLivelloAutorizzazione() {
        return livelloAutorizzazione;
    }

    public void setLivelloAutorizzazione(int livelloAutorizzazione) {
        this.livelloAutorizzazione = livelloAutorizzazione;
    }
}
