package Server.Controller;

import Server.ServerUtil.ServizioEsternoHolder;

import java.util.List;

/**
 * Created by gioele on 16/02/16.
 */
public interface Observer {

    void update(ControllerFacade controller);

    void setNome(String nome);
    void setServiziEsterni(List<ServizioEsternoHolder> serviziEsterni);

}
