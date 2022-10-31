package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Ubicacion",
        foreignKeys = @ForeignKey(entity = Ciudad.class, parentColumns = "id", childColumns = "ciudadID"))
public class Ubicacion {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    private double lat;
    private double lng;
    private String calle;
    private String numero;
    private Integer ciudadID;
    @Ignore
    private Ciudad ciudad;

    public Ubicacion(){

    }

    public Ubicacion(Integer id, double lat, double lng, String calle, String numero, Ciudad ciudad, Integer ciudadID) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.ciudadID = ciudadID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(Integer ciudadID) {
        this.ciudadID = ciudadID;
    }
}
