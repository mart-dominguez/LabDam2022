package com.mdgz.dam.labdam2022.model;

public abstract class Alojamiento {

    protected Integer id;
    protected String titulo;
    protected String descripcion;
    protected Integer capacidad;
    protected Double precioBase;
    protected String imageUrl;

    public abstract Ubicacion getUbicacion();
    public Double costoDia(){
        return precioBase;
    }

    public Alojamiento(){
        super();
    }

    public Alojamiento(Integer id, String titulo, String descripcion, Integer capacidad, Double precioBase, String imageUrl) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
        this.imageUrl = imageUrl;
    }

    public String getTitulo(){
        return  titulo;
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

    public String getImageUrl() {
        return imageUrl;
    }


}
