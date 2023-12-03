/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trafficsoap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author onsjb
 */
@WebService(serviceName = "TrafficWS")
public class TrafficService {
	
	@WebMethod(operationName = "getAllTraffics")
    public List<Traffic> getAllTraffics() {
        List<Traffic> traffics = new ArrayList<>();

        try {
            // Obtenez la connexion à la base de données
            java.sql.Connection conn = Connection.getCon();

            // Créez la déclaration SQL
            Statement st = conn.createStatement();

            // Exécutez la requête SQL
            ResultSet resultSet = st.executeQuery("SELECT id,latitude,longitude,etat,nom_rue,ville,municipalite FROM traffic");

            // Parcourez les résultats et ajoutez-les à la liste
            while (resultSet.next()) {
                Traffic traffic = new Traffic();
                traffic.setId(resultSet.getInt("id"));
                traffic.setLatitude(resultSet.getFloat("latitude"));
                traffic.setLongitude(resultSet.getFloat("longitude"));
                traffic.setEtat(resultSet.getString("etat"));
                traffic.setNomRue(resultSet.getString("nom_rue"));
                traffic.setVille(resultSet.getString("ville"));
                traffic.setMunicipalite(resultSet.getString("municipalite"));
                traffics.add(traffic);
            }

            // Fermez la connexion
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions correctement dans une application réelle
        }

        return traffics;
    }
	
	
	@WebMethod(operationName = "getTrafficByVille")
    public List<Traffic> getTrafficByVille(@WebParam(name = "ville") String ville) {
        List<Traffic> traffics = new ArrayList<>();

        try {
            // Obtenez la connexion à la base de données
            java.sql.Connection conn = Connection.getCon();

            // Créez la déclaration SQL
            Statement st = conn.createStatement();

            // Exécutez la requête SQL
            ResultSet resultSet = st.executeQuery("SELECT id,latitude,longitude,etat,nom_rue,ville,municipalite FROM traffic WHERE ville = '"+ville+"'");

            // Parcourez les résultats et ajoutez-les à la liste
            while (resultSet.next()) {
                Traffic traffic = new Traffic();
                traffic.setId(resultSet.getInt("id"));
                traffic.setLatitude(resultSet.getFloat("latitude"));
                traffic.setLongitude(resultSet.getFloat("longitude"));
                traffic.setEtat(resultSet.getString("etat"));
                traffic.setNomRue(resultSet.getString("nom_rue"));
                traffic.setVille(resultSet.getString("ville"));
                traffic.setMunicipalite(resultSet.getString("municipalite"));
                traffics.add(traffic);
            }

            // Fermez la connexion
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions correctement dans une application réelle
        }

        return traffics;
    }
	
	@WebMethod(operationName = "getTrafficByLatitudeAndLongitude")
    public Traffic getTrafficByLatitudeAndLongitude(@WebParam(name = "latitude") float latitude, @WebParam(name = "longitude") float longitude) {
		  Traffic traffic = new Traffic();
        

        try { 
            // Obtenez la connexion à la base de données
            java.sql.Connection conn = Connection.getCon();

            // Créez la déclaration SQL
            Statement st = conn.createStatement();

            // Exécutez la requête SQL
            ResultSet resultSet = st.executeQuery("SELECT id,latitude,longitude,etat,nom_rue,ville,municipalite FROM traffic WHERE latitude = '"+latitude+"' AND longitude = '"+longitude+"'");

            // Parcourez les résultats et ajoutez-les à la liste
            while (resultSet.next()) {
            	  System.out.println("deb while");
              
                traffic.setId(resultSet.getInt("id"));
                traffic.setLatitude(resultSet.getFloat("latitude"));
                traffic.setLongitude(resultSet.getFloat("longitude"));
                traffic.setEtat(resultSet.getString("etat"));
                traffic.setNomRue(resultSet.getString("nom_rue"));
                traffic.setVille(resultSet.getString("ville"));
                traffic.setMunicipalite(resultSet.getString("municipalite"));
                
            }
            System.out.println("trafics "+traffic);
            // Fermez la connexion
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions correctement dans une application réelle
        }

        return traffic;
    }
	
	
	
	
	
	
	
	
}
