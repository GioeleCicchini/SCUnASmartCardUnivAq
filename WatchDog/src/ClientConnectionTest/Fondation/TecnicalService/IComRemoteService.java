package ClientConnectionTest.Fondation.TecnicalService;

import Util.DTO;

import java.io.IOException;

/**
 * Created by gioele on 04/03/16.
 */
public interface IComRemoteService {

    Object RichiediAlServer(DTO dto,String indirizzo,Integer porta) throws IOException, ClassNotFoundException;


}
