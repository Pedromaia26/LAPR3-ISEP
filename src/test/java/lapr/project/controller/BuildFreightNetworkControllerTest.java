package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildFreightNetworkControllerTest {

    Company c = App.getInstance().getCompany();
    BuildFreightNetworkController controller = new BuildFreightNetworkController(c);
    Country country1 = new Country("Europa", "PT", "PRT", "Portugal", 0.5f, "Lisboa", 20, 40);
    Country country2 = new Country("Europa", "ES", "ESP", "Espanha", 0.5f, "Madrid", 30, 20);
    Country country3 = new Country("Europa", "FR", "FRA", "Franca", 0.5f, "Paris", 40, 30);
    Country country4 = new Country("Europa", "SU", "SUI", "Suica", 0.5f, "Berna", 50, 10);
    Country country5 = new Country("Europa", "GE", "GER", "Alemanha", 0.5f, "Berlim", 35, 25);
    Border border1 = new Border(country1, country2);
    Border border2 = new Border(country2, country3);
    Border border3 = new Border(country3, country4);
    Border border4 = new Border(country3, country5);
    Border border5 = new Border(country4, country5);
    Seadist seadist1 = new Seadist("Portugal", 12345, "Port1", "Espanha", 12346, "Port2", 100);
    Seadist seadist2 = new Seadist("Portugal", 12345, "Port1", "Franca", 12346, "Port3", 50);
    Seadist seadist3 = new Seadist("Portugal", 12345, "Port1", "Suica", 12346, "Port4", 250);
    Seadist seadist4 = new Seadist("Portugal", 12345, "Port1", "Alemanha", 12346, "Port5", 25);

    @Test
    void BuildFreightNetwork0() throws IOException {
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
        Port port2 = new Port("12346", "Port2", "Europa", "Espanha", "23", "26");
        Port port3 = new Port("12347", "Port3", "Europa", "Franca", "21", "27");
        Port port4 = new Port("12348", "Port4", "Europa", "Suica", "22", "28");
        Port port5 = new Port("12349", "Port5", "Europa", "Alemanha", "24", "25");
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
        controller.BuildFreightNetwork(0);
    }

    @Test
    void BuildFreightNetwork5() throws IOException {
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
        Port port1 = new Port("12345", "Port1", "Europa", "Portugal", "25", "25");
        Port port2 = new Port("12346", "Port2", "Europa", "Espanha", "22", "26");
        Port port3 = new Port("12347", "Port3", "Europa", "Franca", "23", "22");
        Port port4 = new Port("12348", "Port4", "Europa", "Suica", "30", "28");
        Port port5 = new Port("12349", "Port5", "Europa", "Alemanha", "21", "29");
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
        controller.BuildFreightNetwork(5);
    }

    @Test
    void CapitalsConnection() throws IOException {
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
        Port port1 = new Port("12345", "Port1", "Europa", "Portugal", "25", "25");
        Port port2 = new Port("12346", "Port2", "Europa", "Espanha", "22", "26");
        Port port3 = new Port("12347", "Port3", "Europa", "Franca", "23", "22");
        Port port4 = new Port("12348", "Port4", "Europa", "Suica", "30", "28");
        Port port5 = new Port("12349", "Port5", "Europa", "Alemanha", "21", "29");
        List<KDTPort.Node<Port>> lista = new ArrayList<>();
        lista.add(new KDTPort.Node<>(port1, port1.getLatitude(), port1.getLongitude()));
        lista.add(new KDTPort.Node<>(port2, port2.getLatitude(), port2.getLongitude()));
        lista.add(new KDTPort.Node<>(port3, port3.getLatitude(), port3.getLongitude()));
        lista.add(new KDTPort.Node<>(port4, port4.getLatitude(), port4.getLongitude()));
        lista.add(new KDTPort.Node<>(port5, port5.getLatitude(), port5.getLongitude()));
        c.getKdtPorts().buildTree(lista);
        List<GraphElement> listCapitals = new ArrayList<>();
        c.getSeadistStore().addSeadist(seadist1);
        c.getSeadistStore().addSeadist(seadist2);
        c.getSeadistStore().addSeadist(seadist3);
        c.getSeadistStore().addSeadist(seadist4);
        for (Country country : c.getCountryStore().getCountries()){ //Adiciona capitais
            listCapitals.add(new GraphElement(country));
        }
        MatrixGraph<GraphElement, Double> graph = new MatrixGraph<>(true);
        controller.CapitalsConnection(listCapitals, graph);
    }

    @Test
    void PortsConnection() throws IOException {
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
        Port port1 = new Port("12345", "Port1", "Europa", "Portugal", "25", "25");
        Port port2 = new Port("12346", "Port2", "Europa", "Espanha", "22", "26");
        Port port3 = new Port("12347", "Port3", "Europa", "Franca", "23", "22");
        Port port4 = new Port("12348", "Port4", "Europa", "Suica", "30", "28");
        Port port5 = new Port("12349", "Port5", "Europa", "Alemanha", "21", "29");
        List<KDTPort.Node<Port>> lista = new ArrayList<>();
        lista.add(new KDTPort.Node<>(port1, port1.getLatitude(), port1.getLongitude()));
        lista.add(new KDTPort.Node<>(port2, port2.getLatitude(), port2.getLongitude()));
        lista.add(new KDTPort.Node<>(port3, port3.getLatitude(), port3.getLongitude()));
        lista.add(new KDTPort.Node<>(port4, port4.getLatitude(), port4.getLongitude()));
        lista.add(new KDTPort.Node<>(port5, port5.getLatitude(), port5.getLongitude()));
        c.getKdtPorts().buildTree(lista);
        List<GraphElement> listPorts = new ArrayList<>();
        c.getSeadistStore().addSeadist(seadist1);
        c.getSeadistStore().addSeadist(seadist2);
        c.getSeadistStore().addSeadist(seadist3);
        c.getSeadistStore().addSeadist(seadist4);
        for (Port port : (List<Port>) c.getKdtPorts().inOrder()){ //Adiciona portos
            listPorts.add(new GraphElement(port));
        }
        MatrixGraph<GraphElement, Double> graph = new MatrixGraph<>(true);
        controller.PortsConnection(listPorts, graph);
    }

    @Test
    void PortCapitalConnection() throws IOException {
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
        Port port1 = new Port("12345", "Port1", "Europa", "Portugal", "25", "25");
        Port port2 = new Port("12346", "Port2", "Europa", "Espanha", "22", "26");
        Port port3 = new Port("12347", "Port3", "Europa", "Franca", "23", "22");
        Port port4 = new Port("12348", "Port4", "Europa", "Suica", "30", "28");
        Port port5 = new Port("12349", "Port5", "Europa", "Alemanha", "21", "29");
        List<KDTPort.Node<Port>> lista = new ArrayList<>();
        lista.add(new KDTPort.Node<>(port1, port1.getLatitude(), port1.getLongitude()));
        lista.add(new KDTPort.Node<>(port2, port2.getLatitude(), port2.getLongitude()));
        lista.add(new KDTPort.Node<>(port3, port3.getLatitude(), port3.getLongitude()));
        lista.add(new KDTPort.Node<>(port4, port4.getLatitude(), port4.getLongitude()));
        lista.add(new KDTPort.Node<>(port5, port5.getLatitude(), port5.getLongitude()));
        c.getKdtPorts().buildTree(lista);
        List<GraphElement> listPorts = new ArrayList<>();
        List<GraphElement> listCapitals = new ArrayList<>();
        c.getSeadistStore().addSeadist(seadist1);
        c.getSeadistStore().addSeadist(seadist2);
        c.getSeadistStore().addSeadist(seadist3);
        c.getSeadistStore().addSeadist(seadist4);
        for (Port port : (List<Port>) c.getKdtPorts().inOrder()){ //Adiciona portos
            listPorts.add(new GraphElement(port));
        }
        for (Country country : c.getCountryStore().getCountries()){ //Adiciona capitais
            listCapitals.add(new GraphElement(country));
        }
        MatrixGraph<GraphElement, Double> graph = new MatrixGraph<>(true);
        controller.PortCapitalConnection(listPorts, listCapitals, graph);
    }

    @Test
    void nClosestPort() throws IOException {
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
        Port port1 = new Port("12345", "Port1", "Europa", "Portugal", "25", "25");
        Port port2 = new Port("12346", "Port2", "Europa", "Espanha", "22", "26");
        Port port3 = new Port("12347", "Port3", "Europa", "Franca", "23", "22");
        Port port4 = new Port("12348", "Port4", "Europa", "Suica", "30", "28");
        Port port5 = new Port("12349", "Port5", "Europa", "Alemanha", "21", "29");
        List<KDTPort.Node<Port>> lista = new ArrayList<>();
        lista.add(new KDTPort.Node<>(port1, port1.getLatitude(), port1.getLongitude()));
        lista.add(new KDTPort.Node<>(port2, port2.getLatitude(), port2.getLongitude()));
        lista.add(new KDTPort.Node<>(port3, port3.getLatitude(), port3.getLongitude()));
        lista.add(new KDTPort.Node<>(port4, port4.getLatitude(), port4.getLongitude()));
        lista.add(new KDTPort.Node<>(port5, port5.getLatitude(), port5.getLongitude()));
        c.getKdtPorts().buildTree(lista);
        List<GraphElement> listPorts = new ArrayList<>();
        List<GraphElement> listCapitals = new ArrayList<>();
        c.getSeadistStore().addSeadist(seadist1);
        c.getSeadistStore().addSeadist(seadist2);
        c.getSeadistStore().addSeadist(seadist3);
        c.getSeadistStore().addSeadist(seadist4);
        for (Port port : (List<Port>) c.getKdtPorts().inOrder()){ //Adiciona portos
            listPorts.add(new GraphElement(port));
        }
        int n = 5;
        MatrixGraph<GraphElement, Double> graph = new MatrixGraph<>(true);
        controller.nClostestPorts(listPorts, graph, n);
    }
}