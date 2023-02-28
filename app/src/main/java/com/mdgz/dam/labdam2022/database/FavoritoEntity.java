package com.mdgz.dam.labdam2022.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.UUID;

public class FavoritoEntity {
    @SerializedName("alojamientoId")
    @Expose
    UUID alojamientoID;
    @SerializedName("usuarioId")
    @Expose
    UUID usuarioID;

    public FavoritoEntity(UUID alojamientoId, UUID usuarioId) {
        this.alojamientoID = alojamientoId;
        this.usuarioID = usuarioId;
    }

    public UUID getAlojamientoID() {
        return alojamientoID;
    }

    public void setAlojamientoID(UUID alojamientoID) {
        this.alojamientoID = alojamientoID;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }
}
