package Server.Settings;

/**
 * Created by Febe on 28/12/2016.
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Server.ServerUtil.ServizioEsternoHolder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserXMLFile {

    private static ParserXMLFile singletonInstance = null;

    public static ParserXMLFile getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ParserXMLFile();
        }
        return singletonInstance;
    }

    public void LeggiImpostazioni() throws IOException {

        try {

            File fXmlFile = new File("../ImpostazioniServer.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);


            doc.getDocumentElement().normalize();

            // Imposto i primi  servparametri deler. Nome e porta su cui verra aperto
            ServerParameter.getSingletonInstance().setNome(doc.getElementsByTagName("nome").item(0).getTextContent());
            ServerParameter.getSingletonInstance().setPorta(Integer.parseInt(doc.getElementsByTagName("porta").item(0).getTextContent()));

            // costruisco una lista con tutti i servizi
            NodeList nList = doc.getElementsByTagName("servizio");

            // variabili di appoggio
            String Servizio = null;
            List<ServizioEsternoHolder> serviziEsterni = new ArrayList<>();

            // scandisco la lista con i servizi
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    // imposto variabile con nome servizio
                    Servizio = eElement.getElementsByTagName("nome").item(0).getTextContent();

                    //aggiungo il servizio ai parametri del server
                    ServerParameter.getSingletonInstance().AddServizioMonitorato(Servizio);

                    // costruisco la lista con tutti i servizi esterni
                    NodeList serviziEsternList = eElement.getElementsByTagName("ServizioEsternoHolder");

                    // scandisco la lista con i servizi esterni
                    for (int temp1 = 0; temp1 < serviziEsternList.getLength(); temp1++) {

                        Node nNodeext = serviziEsternList.item(temp1);

                         if(nNodeext.getNodeType() == Node.ELEMENT_NODE){

                            Element eElementext = (Element) nNodeext;

                            // creo un oggetto che contiene tutti i dati del servizio esterno
                            ServizioEsternoHolder extSer = new ServizioEsternoHolder();
                            extSer.setNome(eElementext.getElementsByTagName("nome").item(0).getTextContent());
                            extSer.setIndirizzoIp(eElementext.getElementsByTagName("indirizzoIp").item(0).getTextContent());
                            extSer.setPorta(Integer.parseInt( eElementext.getElementsByTagName("porta").item(0).getTextContent()));
                            serviziEsterni.add(extSer);

                        }
                    }

                    //  aggiungo servizio da monitorare
                    ServiziManager.getSingletonInstance().addServizio(Servizio,serviziEsterni);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
