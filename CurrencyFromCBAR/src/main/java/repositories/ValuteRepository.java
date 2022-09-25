/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositories;

import java.math.BigDecimal;
import java.util.List;
import models.Valute;
import org.w3c.dom.Document;

/**
 *
 * @author RoRSh-PC
 */
public interface ValuteRepository {

    //Find Name of Valute By Index
    public String findValuteNameByIndex(int index) throws Exception;

    //Find Nominal of Valute By Index
    public String findValuteNominalByIndex(int index) throws Exception;

    //Find Valute of Valute By Index
    public double findValueOfValuteByIndex(int index) throws Exception;

    //Return ALL Data From XML(Document)
    public List<Valute> listAllDataFromXML() throws Exception;

    //Connection to URL AND Parsing Data From XML to Document
    public Document connectionURL() throws Exception;
}
