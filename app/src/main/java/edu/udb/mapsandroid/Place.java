package edu.udb.mapsandroid;

import java.io.Serializable;

/**
 * Clase POJO para objectos Place
 */
public class Place implements Serializable{

    //Usar Refactor -> Encapsulate Fields para generar campos
    private String placeName;
    private double lat;
    private double lon;

    //Utilizar Generate->Constructor
    public Place(String placeName, double lat, double lon) {
        this.placeName = placeName;
        this.lat = lat;
        this.lon = lon;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
