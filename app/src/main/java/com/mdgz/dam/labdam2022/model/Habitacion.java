package com.mdgz.dam.labdam2022.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.UUID;

@Entity(tableName = "Habitacion",
        foreignKeys = @ForeignKey(entity = Hotel.class, parentColumns = "id", childColumns = "hotelID"))
public class Habitacion extends Alojamiento {

    private Integer camasIndividuales;
    private Integer camasMatrimoniales;
    private Boolean tieneEstacionamiento;
    private Integer hotelID;
    @Ignore
    private Hotel hotel;

    public Habitacion() {
        super();
    }

    public Habitacion(UUID id, String titulo, String descripcion, Integer capacidad, Double precioBase, Integer camasIndividuales, Integer camasMatrimoniales, Boolean tieneEstacionamiento, Hotel hotel, Integer hotelID)
    {
        super(id, titulo, descripcion, capacidad, precioBase);
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotel = hotel;
        this.hotelID = hotelID;
    }

    public int getCamasIndividuales() {
        return camasIndividuales;
    }

    public void setCamasIndividuales(Integer camasIndividuales) {
        this.camasIndividuales = camasIndividuales;
    }

    public int getCamasMatrimoniales() {
        return camasMatrimoniales;
    }

    public void setCamasMatrimoniales(Integer camasMatrimoniales) {
        this.camasMatrimoniales = camasMatrimoniales;
    }

    public Boolean getTieneEstacionamiento() {
        return tieneEstacionamiento;
    }

    public void setTieneEstacionamiento(Boolean tieneEstacionamiento) {
        this.tieneEstacionamiento = tieneEstacionamiento;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public Ubicacion getUbicacion() {
        return hotel.getUbicacion();
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }
}
