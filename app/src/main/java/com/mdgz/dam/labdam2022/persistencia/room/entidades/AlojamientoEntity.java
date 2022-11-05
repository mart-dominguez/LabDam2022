package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.persistencia.enumerados.TipoAlojamiento;

import java.util.UUID;

@Entity(tableName = "Alojamiento")
public class AlojamientoEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private UUID id;
    private String  titulo;
    private String  descripcion;
    private Integer capacidad;
    private Double  precioBase;
    private TipoAlojamiento tipo;

    public AlojamientoEntity(@NonNull UUID id, String titulo, String descripcion, Integer capacidad, Double precioBase, TipoAlojamiento tipo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.tipo = tipo;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
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

    public TipoAlojamiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAlojamiento tipo) {
        this.tipo = tipo;
    }
}
