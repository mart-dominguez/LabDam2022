package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Ciudad;


@Entity(tableName = "Ubicacion",
        foreignKeys = @ForeignKey(entity = CiudadEntity.class, parentColumns = "id", childColumns = "ciudadID"),
        indices = {@Index(value = {"ciudadID"})})

public class UbicacionEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    private double lat;
    private double lng;
    private String calle;
    private String numero;
    private Integer ciudadID;

    public UbicacionEntity(Integer id, double lat, double lng, String calle, String numero, Integer ciudadID) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.calle = calle;
        this.numero = numero;
        this.ciudadID = ciudadID;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
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

    public Integer getCiudadID() {
        return ciudadID;
    }

    public void setCiudadID(Integer ciudadID) {
        this.ciudadID = ciudadID;
    }
}
