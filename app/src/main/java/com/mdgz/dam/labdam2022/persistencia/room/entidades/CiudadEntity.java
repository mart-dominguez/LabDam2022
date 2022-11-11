package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Ciudad")
public class CiudadEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;
    private String nombre;
    private String abreviatura;

    public CiudadEntity(Integer id, String nombre, String abreviatura)
    {
        this.id = id;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
}
