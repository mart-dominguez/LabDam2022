package com.mdgz.dam.labdam2022.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.UUID;

@Entity(tableName = "Departamento",
        foreignKeys = @ForeignKey(entity = Ubicacion.class, parentColumns = "id", childColumns = "ubicacionID"))
public class Departamento extends Alojamiento{

    private Boolean tieneWifi;
    private Double costoLimpieza;
    private Integer cantidadHabitaciones;
    private Integer ubicacionID;
    @Ignore
    private Ubicacion ubicacion;


    public Departamento(){
        super();
    }

    public Departamento(UUID id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones, Ubicacion ubicacion, Integer ubicacionID) {
        super(id, titulo, descripcion, capacidad, precioBase);
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacion = ubicacion;
        this.ubicacionID = ubicacionID;
    }

    public Boolean getTieneWifi() {
        return tieneWifi;
    }

    public void setTieneWifi(Boolean tieneWifi) {
        this.tieneWifi = tieneWifi;
    }

    public Double getCostoLimpieza() {
        return costoLimpieza;
    }

    public void setCostoLimpieza(Double costoLimpieza) {
        this.costoLimpieza = costoLimpieza;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public void setUbicacionID(Integer ubicacionID) {
        this.ubicacionID = ubicacionID;
    }

    public Integer getUbicacionID() {
        return ubicacionID;
    }

    @Override
    public Ubicacion getUbicacion() {
        return ubicacion;
    }


}
