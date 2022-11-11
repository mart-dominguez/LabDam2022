package com.mdgz.dam.labdam2022.persistencia.room.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "reserva_habitacion",
        foreignKeys = {
        @ForeignKey(entity = HabitacionEntity.class, parentColumns = "id", childColumns = "habitacionID"),
                @ForeignKey(entity = UsuarioEntity.class, parentColumns = "id", childColumns = "usuarioID")},
        indices = {@Index(value = {"habitacionID"}),@Index(value = {"usuarioID"})})

public class ReservaHabitacionEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private UUID id;
    private UUID habitacionID;     //M..0.1
    private UUID usuarioID;         //M..0.1
    private Date fechaIngreso;
    private Date fechaSalida;

    public ReservaHabitacionEntity(@NonNull UUID id, UUID habitacionID, UUID usuarioID, Date fechaIngreso, Date fechaSalida) {
        this.id = id;
        this.habitacionID = habitacionID;
        this.usuarioID = usuarioID;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public UUID getHabitacionID() {
        return habitacionID;
    }

    public void setHabitacionID(UUID habitacionID) {
        this.habitacionID = habitacionID;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
