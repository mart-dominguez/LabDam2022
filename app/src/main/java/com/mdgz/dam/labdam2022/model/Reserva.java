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
    private Usuario usuario;
    private Date fechaIngreso;
    private Date fechaSalida;

    //private Instant fechaIngreso;
    //private Instant fechaEgreso;
    //private Boolean cancelada;
    //private Double monto;

    //@Ignore
    private Alojamiento alojamiento;

    public Reserva(UUID id, Usuario usuario, Date fechaIngreso, Date fechaSalida, Alojamiento alojamiento){
        this.id = id;
        this.usuario = usuario;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.alojamiento = alojamiento;
    }

    @NonNull
    public UUID getId() {
        return id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
