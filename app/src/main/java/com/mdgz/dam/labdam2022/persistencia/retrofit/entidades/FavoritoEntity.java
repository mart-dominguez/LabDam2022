package com.mdgz.dam.labdam2022.persistencia.retrofit.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/*
    Anotaciones para gson: https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/annotations/package-summary.html
 */
import java.util.UUID;

public class FavoritoEntity {
    @SerializedName("alojamientoId")
    @Expose
    private UUID alojamientoId;
    @SerializedName("usuarioId")
    @Expose
    private UUID usuarioId;

    public FavoritoEntity(UUID alojamientoId, UUID usuarioId) {
        this.alojamientoId = alojamientoId;
        this.usuarioId = usuarioId;
    }

    public UUID getAlojamientoId() {
        return alojamientoId;
    }

    public void setAlojamientoId(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }
}
