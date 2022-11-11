package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "Hotel",
        foreignKeys = @ForeignKey(entity = UbicacionEntity.class, parentColumns = "id", childColumns = "ubicacionID"),
        indices = {@Index(value = {"ubicacionID"})})

public class HotelEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    private String nombre;
    private Integer categoria;
    private Integer ubicacionID;

    public HotelEntity(Integer id, String nombre, Integer categoria, Integer ubicacionID) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ubicacionID = ubicacionID;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
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

    public Integer getUbicacionID() {
        return ubicacionID;
    }

    public void setUbicacionID(Integer ubicacionID) {
        this.ubicacionID = ubicacionID;
    }
}
