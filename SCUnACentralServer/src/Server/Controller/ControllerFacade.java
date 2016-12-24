package Server.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Util.DTO;
/**
 * Created by gioele on 16/02/16.
 */
public class ControllerFacade implements IController {

    private String funzione;
    private List<Serializable> oggettiPersistenti;
    private List<Observer> osservatoriRichieste = new ArrayList<Observer>();


    public List<Observer> getOsservatoriRichieste() {
        return osservatoriRichieste;
    }

    public void setOsservatoriRichieste(List<Observer> osservatoriRichieste) {
        this.osservatoriRichieste = osservatoriRichieste;
    }

    public void ControllerFacade() {

    }


    public String getFunzione() {
        return this.funzione;
    }


    public void ArrivaRichiesta(DTO dto) {
        this.funzione = dto.getFunzione();
        this.oggettiPersistenti = dto.getOggettiTrasferimento();

        this.Notify();
    }

    @Override
    public void Attach(Observer o) {
        osservatoriRichieste.add(o);
    }

    @Override
    public void Detach(Observer o) {
        osservatoriRichieste.remove(o);
    }

    @Override
    public void Notify() {
        for (Observer osservatore : osservatoriRichieste) {
            osservatore.update(this);
        }
    }

    private void prelevato() {
        this.funzione = null;
        this.oggettiPersistenti.clear();

    }

    public List<Serializable> getOggettiPersistenti() {
        return oggettiPersistenti;
    }

    public void setOggettiPersistenti(List<Serializable> oggettiPersistenti) {
        this.oggettiPersistenti = oggettiPersistenti;
    }
}