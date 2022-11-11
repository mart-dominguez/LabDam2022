package com.mdgz.dam.labdam2022.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.UUID;

public class Departamento extends Alojamiento {

    private Boolean tieneWifi;
    private Double costoLimpieza;
    private Integer cantidadHabitaciones;

    private Ubicacion ubicacion;


    public Departamento(){
        super();
    }

    public Departamento(UUID id, String titulo, String descripcion, Integer capacidad, Double precioBase, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones, Ubicacion ubicacion) {
        super(id, titulo, descripcion, capacidad, precioBase);
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacion = ubicacion;
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


    @Override
    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion){this.ubicacion = ubicacion;
    }


}
