/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trafficsoap;

/**
 *
 * @author onsjb
 */
public class Traffic {
	private int id;
	private float latitude; 
	private float longitude;
	private String etat;
	private String nomRue;
	private String ville;
	private String municipalite;

    public Traffic(int id, float latitude, float longitude, String etat, String nomRue, String ville, String municipalite) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.etat = etat;
        this.nomRue = nomRue;
        this.ville = ville;
        this.municipalite = municipalite;
    }

    public Traffic() {
    }

    public int getId() {
        return id;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getEtat() {
        return etat;
    }

    public String getNomRue() {
        return nomRue;
    }

    public String getVille() {
        return ville;
    }

    public String getMunicipalite() {
        return municipalite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setMunicipalite(String municipalite) {
        this.municipalite = municipalite;
    }
    
    
        
        
}
