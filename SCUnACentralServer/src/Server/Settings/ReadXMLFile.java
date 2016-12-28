package Server.Settings;

/**
 * Created by Febe on 28/12/2016.
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Server.StartServer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLFile {


    private static ReadXMLFile  singletonInstance = null;

    public static ReadXMLFile  getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new ReadXMLFile ();
        }
        return singletonInstance;
    }



    public void LeggiImpostazioni() throws IOException {

        try {

            File fXmlFile = new File("ImpostazioniServer.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);


            doc.getDocumentElement().normalize();



            System.out.println("Nome Server:" + doc.getElementsByTagName("nome").item(0).getTextContent());
            System.out.println("Porta Server :" + doc.getElementsByTagName("porta").item(0).getTextContent());

            ServerParameter.getSingletonInstance().setNome(doc.getElementsByTagName("nome").item(0).getTextContent());
            ServerParameter.getSingletonInstance().setPorta(Integer.parseInt(doc.getElementsByTagName("porta").item(0).getTextContent()));

            NodeList nList = doc.getElementsByTagName("servizio");

            System.out.println("----------------------------");

            String Servizio = null;
            List<servizioEsterno> serviziEsterni = new ArrayList<>();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Servizio = eElement.getElementsByTagName("nome").item(0).getTextContent();
                    System.out.println("\n Servizio :" + eElement.getElementsByTagName("nome").item(0).getTextContent());

                    NodeList servizioEsterno = eElement.getElementsByTagName("servizioEsterno");
                    for (int temp1 = 0; temp1 < servizioEsterno.getLength(); temp1++) {
                        Node nNodeext = servizioEsterno.item(temp1);
                        if(nNodeext.getNodeType() == Node.ELEMENT_NODE){

                            Element eElementext = (Element) nNodeext;
                            System.out.println("Nome Servizio Esterno : " + eElementext.getElementsByTagName("nome").item(0).getTextContent());
                            System.out.println("Indirizzo Ip : " + eElementext.getElementsByTagName("indirizzoIp").item(0).getTextContent());
                            System.out.println("Porta : " + eElementext.getElementsByTagName("porta").item(0).getTextContent());


                            servizioEsterno extSer = new servizioEsterno();
                            extSer.setNome(eElementext.getElementsByTagName("nome").item(0).getTextContent());
                            extSer.setIndirizzoIp(eElementext.getElementsByTagName("indirizzoIp").item(0).getTextContent());
                            extSer.setPorta(Integer.parseInt( eElementext.getElementsByTagName("porta").item(0).getTextContent()));

                            serviziEsterni.add(extSer);



                        }
                    }

                    ServiziManager.getSingletonInstance().addServizio(Servizio,serviziEsterni);

                    System.out.println("Servizio id : " + eElement.getAttribute("id"));
                    System.out.println("");


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StartServer.getSingletonInstance().Start();



    }


}
