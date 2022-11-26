package com.mdgz.dam.labdam2022.model;

public class Ubicacion {
    private double lat;
    private double lng;
    private String calle;
    private String numero;
    private Ciudad ciudad;

    public Ubicacion(){

    }

    public Ubicacion(double lat, double lng, String calle, String numero, Ciudad ciudad) {
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    public String toString(){
        return getCalle()+" "+ getNumero()+", "+ getCiudad();
    }
}
