/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import models.Valute;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import repositories.ValuteRepository;

/**
 *
 * @author RoRSh-PC
 */
public class ValuteService implements ValuteRepository {

    @Override
    public String findValuteNameByIndex(int index) throws Exception {
        return listAllDataFromXML().get(index).getName();
    }

    @Override
    public String findValuteNominalByIndex(int index) throws Exception {
        return listAllDataFromXML().get(index).getNominal();
    }

    @Override
    public double findValueOfValuteByIndex(int index) throws Exception {
        return listAllDataFromXML().get(index).getValue();
    }

    @Override
    public Document connectionURL() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        //URL+Now Date AND Connecttion to URL is opened
        URL url = new URL("https://www.cbar.az/currencies/" + dateFormat.format(new Date().getTime()) + ".xml");
        url.openConnection();

        //XML From URL AND Parsing to Document
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        return documentBuilderFactory.newDocumentBuilder().parse(url.toString());

    }

    @Override
    public List<Valute> listAllDataFromXML() throws Exception {
        int valuteItem = 0;
        List<Valute> listValute = new ArrayList<>();

        //Set returning Document to new Document For use
        Document document = connectionURL();
        document.getDocumentElement().normalize();

        for (; valuteItem < document.getElementsByTagName("Valute").getLength(); valuteItem++) {

            Node nodeValute = document.getElementsByTagName("Valute").item(valuteItem).getAttributes().getNamedItem("Code");

            Valute valute = new Valute();

            //Code
            valute.setCode(nodeValute.getTextContent());
            //Nominal
            valute.setNominal(document.getElementsByTagName("Nominal").item(valuteItem).getTextContent());
            if (document.getElementsByTagName("Nominal").item(valuteItem).getTextContent().contains("100")) {
                valute.setNominal(document.getElementsByTagName("Nominal").item(valuteItem).getTextContent().replace("100", "1"));
            }

            //Name
            valute.setName(document.getElementsByTagName("Name").item(valuteItem).getTextContent());

            if (document.getElementsByTagName("Name").item(valuteItem).getTextContent().contains("1")) {
                valute.setName(document.getElementsByTagName("Name").item(valuteItem).getTextContent().replace("1", ""));
            }

            if (document.getElementsByTagName("Name").item(valuteItem).getTextContent().contains("100")) {
                valute.setName(document.getElementsByTagName("Name").item(valuteItem).getTextContent().replace("100", ""));
            }

            //Value
            if (document.getElementsByTagName("Nominal").item(valuteItem).getTextContent().contains("100")) {
                valute.setValue(
                        Double.valueOf(document.getElementsByTagName("Value").item(valuteItem).getTextContent()) / 100
                );
            } else {
                valute.setValue(
                        Double.valueOf(document.getElementsByTagName("Value").item(valuteItem).getTextContent())
                );
            }

            listValute.add(valute);

        }
        return listValute;

    }

}
