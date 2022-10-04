package com.mdgz.dam.labdam2022.model;

public abstract class Alojamiento {

    protected Integer id;
    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;
    protected Double precioBase;

    public abstract Ubicacion getUbicacion();
    public Double costoDia(){
        return precioBase;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getTitulo(){
        return titulo;
    }

    public Alojamiento(){
        super();
    }

    public Alojamiento(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }
}
