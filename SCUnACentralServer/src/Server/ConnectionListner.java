package Server;

import Server.Controller.ControllerFacade;
import Server.ServerUtil.RispostaMaker;
import Server.UI.ServerGUI;
import Server.UI.UI_ServerFacade;
import Util.DTO;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by gioele on 16/02/16.
 */
public class ConnectionListner {

    private ControllerFacade controllerFacade;
    private ServerSocket welcomeSocket = null;

    public ConnectionListner() {

    }

    public void StartServer() throws IOException {

        try {
            Integer Porta = UI_ServerFacade.getSingletonInstance().getPorta();
            welcomeSocket = new ServerSocket(Porta);
            UI_ServerFacade.getSingletonInstance().reportMessage("Server Aperto");

        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {

            Socket connectionSocket = null;
            try {
                connectionSocket = welcomeSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectInputStream objectInput = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
            DTO dto = null;
            try {

                dto = (DTO) objectInput.readObject();
                String Operazione = dto.getFunzione();
                UI_ServerFacade.getSingletonInstance().reportMessage("Richiesta : "+Operazione);

                controllerFacade.ArrivaRichiesta(dto);


                DTO Risposta = RispostaMaker.getSingletonInstance().PrelevaRisposta();
                objectOutputStream.writeObject(Risposta);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                connectionSocket.close();
            }

        }


    }

    public void setControllerFacade(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }
}