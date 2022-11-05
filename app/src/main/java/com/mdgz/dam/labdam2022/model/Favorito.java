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
    private UUID usuarioID; // raro

    private Alojamiento alojamiento;

    public Favorito(UUID id, UUID usuarioID, Alojamiento alojamiento){
        this.id = id;
        this.usuarioID = usuarioID;
        this.alojamiento = alojamiento;
    }

    @NonNull
    public UUID getId() {
        return id;
    }


    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }
}
