package com.example.dm2.examen2ev;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dm2 on 02/02/2018.
 */

public class RSSParserDom {
    private URL rssURL;

    public RSSParserDom (String url){
        try{
            this.rssURL =new URL (url);
        }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Tiempo> parse() {
        //Instanciamos la fabrica para DOM
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        List<Tiempo> Tiempos = new ArrayList<Tiempo>();
        try {
            //Creamos un nuevo parser DOM
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Realizamos la lectura completa del XML
            Document dom = builder.parse(this.getInputStream());
            //Nos posicionamos en el nodo principal del árbol (<rss>)
            Element root = dom.getDocumentElement();
            //Localizamos todos los elemntos <item>
            NodeList items = root.getElementsByTagName("hora");
            //Recorremos la lista de Tiempos

            Tiempo tiempo = new Tiempo();
            //Obtenemos la Tiempo actual
            Node item = items.item(0);
            try {
                NodeList datosDia = item.getChildNodes();
                String texto;
                for (int z = 1; z < datosDia.getLength(); z++) {
                    Node dato2 = datosDia.item(z);
                    String etiqueta2 = dato2.getNodeName();
                    Element elemento=null;
                    if (etiqueta2.equals("hora_datos")) {
                        texto = obtenerTexto(dato2);
                        tiempo.setHora(texto);
                    } else if (etiqueta2.equals("temperatura")) {
                        texto = obtenerTexto(dato2);
                        tiempo.setTemp(texto);
                    }else if (etiqueta2.equals("texto")){
                        texto = obtenerTexto(dato2);
                        tiempo.setCielo(texto);
                    }
                }
            }catch(ClassCastException e) {
            }
            Tiempos.add(tiempo);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return Tiempos;
    }
    public String obtenerTexto (Node dato) {
        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();
        for (int k=0; k<fragmentos.getLength(); k++) {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }
    private InputStream getInputStream() {
        try {
            return rssURL.openConnection().getInputStream();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
