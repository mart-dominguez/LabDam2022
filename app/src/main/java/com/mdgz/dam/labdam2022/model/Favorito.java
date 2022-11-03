package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "Favorito",
        foreignKeys = {@ForeignKey(entity = Departamento.class, parentColumns = "id", childColumns = "departamentoID"),
                       @ForeignKey(entity = Habitacion.class, parentColumns = "id", childColumns = "habitacionID")})
public class Favorito {

    @PrimaryKey//(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private UUID id;
    //private UUID alojamientoID;
    private UUID habitacionID;
    private UUID departamentoID;
    private UUID usuarioID; // raro

    @Ignore
    private Alojamiento alojamiento;

    public Favorito(UUID id, UUID habitacionID, UUID departamentoID, UUID usuarioID){
        this.id = id;
        this.habitacionID = habitacionID;
        this.departamentoID = departamentoID;
        this.usuarioID = usuarioID;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public UUID getHabitacionID() {
        return habitacionID;
    }

    public UUID getDepartamentoID() {
        return departamentoID;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public void setHabitacionID(UUID habitacionID) {
        this.habitacionID = habitacionID;
    }

    public void setDepartamentoID(UUID departamentoID) {
        this.departamentoID = departamentoID;
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
