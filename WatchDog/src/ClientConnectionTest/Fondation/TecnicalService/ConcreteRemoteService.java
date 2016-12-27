package ClientConnectionTest.Fondation.TecnicalService;

import Util.DTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by gioele on 04/03/16.
 */
public class ConcreteRemoteService implements IComRemoteService {


    private static ConcreteRemoteService singletonInstance = null;


    public static ConcreteRemoteService getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ConcreteRemoteService();
        }
        return singletonInstance;
    }


    @Override
    public DTO RichiediAlServer(DTO dto,String indirizzo,Integer porta) throws IOException, ClassNotFoundException {
        Socket clientSocket = new Socket(indirizzo, porta);
        DTO risp = null;
        try {
            ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutput.writeObject(dto);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                risp = (DTO) objectInputStream.readObject();
            }catch (SocketException e){
                throw new IOException();
            }
            catch (IOException e) {
                e.printStackTrace();
            } finally {
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("nessuno in ascolto profondo");
           throw new IOException();
        }




        return risp;
    }

}
