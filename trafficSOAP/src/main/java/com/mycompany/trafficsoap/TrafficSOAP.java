/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.trafficsoap;

import jakarta.xml.ws.Endpoint;

/**
 *
 * @author onsjb
 */
public class TrafficSOAP {

    public static void main(String[] args) {
        String url ="http://localhost:8887/";
        Endpoint.publish(url, new TrafficService());
        System.out.println("Web Service déployé sur l'adresse " + url);
    }
}
