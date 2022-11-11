package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;


public class Favorito {

    private UUID id;
    private Usuario usuario;

    private Alojamiento alojamiento;

    public Favorito(UUID id, Usuario usuario, Alojamiento alojamiento){
        this.id = id;
        this.usuario = usuario;
        this.alojamiento = alojamiento;
    }

    @NonNull
    public UUID getId() {
        return id;
    }


    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
