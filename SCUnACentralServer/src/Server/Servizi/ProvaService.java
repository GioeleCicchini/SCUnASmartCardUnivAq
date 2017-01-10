package Server.Servizi;

import Server.Controller.ControllerFacade;
import Server.Controller.Observer;
import Server.ServerUtil.ServizioEsternoHolder;

import java.util.List;

/**
 * Created by Febe on 09/01/2017.
 */
public class ProvaService implements Observer {
    @Override
    public void update(ControllerFacade controller) {

        if (controller.getFunzione().equals("Prova")) {


            System.out.println("prova");



        }



    }

    @Override
    public void setNome(String nome) {

    }

    @Override
    public void setServiziEsterni(List<ServizioEsternoHolder> serviziEsterni) {

    }
}
