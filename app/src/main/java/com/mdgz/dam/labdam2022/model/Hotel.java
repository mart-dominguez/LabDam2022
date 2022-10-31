package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Hotel",
        foreignKeys = @ForeignKey(entity = Ubicacion.class, parentColumns = "id", childColumns = "ubicacionID"))
public class Hotel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    private String nombre;
    private Integer categoria;
    private Integer ubicacionID;
    @Ignore
    private Ubicacion ubicacion;

    public Hotel(){
        super();
    }

    public Hotel(Integer id, String nombre, Integer categoria, Ubicacion ubicacion, Integer ubicacionID) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.ubicacionID = ubicacionID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getUbicacionID() {
        return ubicacionID;
    }

    public void setUbicacionID(Integer ubicacionID) {
        this.ubicacionID = ubicacionID;
    }
}
