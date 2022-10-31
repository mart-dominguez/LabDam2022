package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
import java.util.UUID;

@Entity(tableName = "Alojamiento")
public abstract class Alojamiento {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private UUID id;
    private String  titulo;
    private String  descripcion;
    private Integer capacidad;
    private Double  precioBase;

    public abstract Ubicacion getUbicacion();

    public Alojamiento(){
        super();
    }

    public Alojamiento(UUID id, String titulo, String descripcion, Integer capacidad, Double precioBase) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alojamiento that = (Alojamiento) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
