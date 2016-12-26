package Server.UI;

/**
 * Created by Febe on 26/12/2016.
 */
public class UI_ServerFacade implements UI_serverFacadeInterface {

    Integer Porta = null;
    ServerGUI serverGUI;


    private static UI_ServerFacade singletonInstance = null;

    public static UI_ServerFacade getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new UI_ServerFacade();
        }
        return singletonInstance;
    }


    @Override
    public void setPorta(int Porta) {
        this.Porta = Porta;
    }

    @Override
    public Integer getPorta() {
        return this.Porta;
    }

    @Override
    public void reportMessage(String messaggio) {
        serverGUI.getSingletonInstance().getRegistroTextArea().append(messaggio+" \n");
    }

    public ServerGUI getServerGUI() {
        return serverGUI;
    }

    public void setServerGUI(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
    }
}
