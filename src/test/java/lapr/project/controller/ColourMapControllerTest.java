package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ColourMapControllerTest {

    @BeforeEach
    public void setup(){
        App.getInstance().setCompany(new Company());
        c = App.getInstance().getCompany();
        country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
        country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
        country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
        country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
        country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);
        border1 = new Border(country1, country2);
        border2 = new Border(country2, country3);
        border3 = new Border(country3, country4);
        border4 = new Border(country3, country5);
        border5 = new Border(country4, country5);
        seadist1 = new Seadist("Portugal", 12345, "Port1", "Espanha", 12346, "Port2", 100);
        seadist2 = new Seadist("Portugal", 12345, "Port1", "Franca", 12346, "Port3", 200);
        seadist3 = new Seadist("Portugal", 12345, "Port1", "Suica", 12346, "Port4", 250);
        seadist4 = new Seadist("Portugal", 12345, "Port1", "Alemanha", 12346, "Port5", 300);

    }

    Company c;
    Country country1;
    Country country2;
    Country country3;
    Country country4;
    Country country5;
    Border border1;
    Border border2;
    Border border3;
    Border border4;
    Border border5;
    Seadist seadist1;
    Seadist seadist2;
    Seadist seadist3;
    Seadist seadist4;

    @Test
    void getCountriesDegree() throws IOException {
        c.getCountryStore().addCountry(country1);
        c.getCountryStore().addCountry(country2);
        c.getCountryStore().addCountry(country3);
        c.getCountryStore().addCountry(country4);
        c.getCountryStore().addCountry(country5);
        c.getBorderStore().addBorder(border1);
        c.getBorderStore().addBorder(border2);
        c.getBorderStore().addBorder(border3);
        c.getBorderStore().addBorder(border4);
        c.getBorderStore().addBorder(border5);
        Port port1 = new Port("12345", "Port1", "Europa", "Portugal", "21", "25");
        Port port2 = new Port("12346", "Port2", "Europa", "Espanha", "22", "26");
        Port port3 = new Port("12347", "Port3", "Europa", "Franca", "23", "27");
        Port port4 = new Port("12348", "Port4", "Europa", "Suica", "24", "28");
        Port port5 = new Port("12349", "Port5", "Europa", "Alemanha", "25", "29");
        List<KDTPort.Node<Port>> listaa = new ArrayList<>();
        listaa.add(new KDTPort.Node<>(port1, port1.getLatitude(), port1.getLongitude()));
        listaa.add(new KDTPort.Node<>(port2, port2.getLatitude(), port2.getLongitude()));
        listaa.add(new KDTPort.Node<>(port3, port3.getLatitude(), port3.getLongitude()));
        listaa.add(new KDTPort.Node<>(port4, port4.getLatitude(), port4.getLongitude()));
        listaa.add(new KDTPort.Node<>(port5, port5.getLatitude(), port5.getLongitude()));
        c.getKdtPorts().buildTree(listaa);
        c.getSeadistStore().addSeadist(seadist1);
        c.getSeadistStore().addSeadist(seadist2);
        c.getSeadistStore().addSeadist(seadist3);
        c.getSeadistStore().addSeadist(seadist4);
        BuildFreightNetworkController controller = new BuildFreightNetworkController(c);
        controller.BuildFreightNetwork(0);
        ColourMapController ccontroller = new ColourMapController(c);
        ArrayList<GraphElement> lista = new ArrayList<>();
        lista.add(new GraphElement(country3));
        lista.add(new GraphElement(country2));
        lista.add(new GraphElement(country4));
        lista.add(new GraphElement(country5));
        lista.add(new GraphElement(country1));
        assertEquals(lista, ccontroller.getCountriesDegree());
    }

    @Test
    void colourMap() throws IOException {
        c.getCountryStore().addCountry(country1);
        c.getCountryStore().addCountry(country2);
        c.getCountryStore().addCountry(country3);
        c.getCountryStore().addCountry(country4);
        c.getCountryStore().addCountry(country5);
        c.getBorderStore().addBorder(border1);
        c.getBorderStore().addBorder(border2);
        c.getBorderStore().addBorder(border3);
        c.getBorderStore().addBorder(border4);
        c.getBorderStore().addBorder(border5);
        Port port1 = new Port("12345", "Port1", "Europa", "Portugal", "21", "25");
        Port port2 = new Port("12346", "Port2", "Europa", "Espanha", "22", "26");
        Port port3 = new Port("12347", "Port3", "Europa", "Franca", "23", "27");
        Port port4 = new Port("12348", "Port4", "Europa", "Suica", "24", "28");
        Port port5 = new Port("12349", "Port5", "Europa", "Alemanha", "25", "29");
        List<KDTPort.Node<Port>> lista = new ArrayList<>();
        lista.add(new KDTPort.Node<>(port1, port1.getLatitude(), port1.getLongitude()));
        lista.add(new KDTPort.Node<>(port2, port2.getLatitude(), port2.getLongitude()));
        lista.add(new KDTPort.Node<>(port3, port3.getLatitude(), port3.getLongitude()));
        lista.add(new KDTPort.Node<>(port4, port4.getLatitude(), port4.getLongitude()));
        lista.add(new KDTPort.Node<>(port5, port5.getLatitude(), port5.getLongitude()));
        c.getKdtPorts().buildTree(lista);
        c.getSeadistStore().addSeadist(seadist1);
        c.getSeadistStore().addSeadist(seadist2);
        c.getSeadistStore().addSeadist(seadist3);
        c.getSeadistStore().addSeadist(seadist4);
        BuildFreightNetworkController controller = new BuildFreightNetworkController(c);
        controller.BuildFreightNetwork(0);
        ColourMapController ccontroller = new ColourMapController(c);
        ccontroller.ColourMap();
    }
}