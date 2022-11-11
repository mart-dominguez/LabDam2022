package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.persistencia.enumerados.TipoAlojamiento;

import java.util.UUID;

//NO se mapea a una tabla SQL
public class AlojamientoEntity {

    private String  titulo;
    private String  descripcion;
    private Integer capacidad;
    private Double  precioBase;

    public AlojamientoEntity(String titulo, String descripcion, Integer capacidad, Double precioBase)
    {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

}
