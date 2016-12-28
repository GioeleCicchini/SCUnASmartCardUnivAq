package Server.Settings;

import Server.Controller.Observer;
import Server.StartServer;

import java.util.List;

/**
 * Created by Febe on 28/12/2016.
 */
public class ServiziManager {


    private static ServiziManager singletonInstance = null;

    public static ServiziManager getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ServiziManager();
        }
        return singletonInstance;
    }

    public void addServizio(String nome, List<servizioEsterno> serviziEsterni) throws ClassNotFoundException {

        System.out.println("aggiungo servizo"+" Server.Servizi" + nome);
            try {
                Class classe = Class.forName("Server.Servizi." + nome);
                Observer servizioIstanziato = (Observer) classe.newInstance();
                servizioIstanziato.setServiziEsterni(serviziEsterni);
                servizioIstanziato.setNome(nome);
                StartServer.getSingletonInstance().addServizio(servizioIstanziato);

            }catch (ClassNotFoundException e){
                System.out.println("Errore classe non trovata");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }


    }




}
