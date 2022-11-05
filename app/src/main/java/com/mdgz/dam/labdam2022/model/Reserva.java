package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;


public class Reserva {

    private UUID id;
    private UUID usuarioID; // raro
    private Date fechaIngreso;
    private Date fechaSalida;

    //private Instant fechaIngreso;
    //private Instant fechaEgreso;
    //private Boolean cancelada;
    //private Double monto;

    //@Ignore
    private Alojamiento alojamiento;

    public Reserva(UUID id, UUID usuarioID, Date fechaIngreso, Date fechaSalida, Alojamiento alojamiento){
        this.id = id;
        this.usuarioID = usuarioID;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.alojamiento = alojamiento;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }
}
