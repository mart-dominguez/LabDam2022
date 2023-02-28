package com.mdgz.dam.labdam2022.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.mdgz.dam.labdam2022.database.ReservaEntity;
import com.mdgz.dam.labdam2022.dateconverters.DateConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

//@Entity()

//@TypeConverters(DateConverter.class)
public class Reserva {
    public Reserva() {
    }

    public Reserva(Date fechaIngreso, Date fechaEgreso, Double monto) {
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.monto = monto;
        this.id = UUID.randomUUID();
    }

   // @PrimaryKey
 //   @NonNull
    @Expose
    private UUID id;
    @Expose
    private Date fechaIngreso;
    @Expose
    private Date fechaEgreso;
    @Expose
    private Double monto;
    //private Boolean cancelada;


  //  @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public ReservaEntity toEntity() {
        return new ReservaEntity(id, id, new java.sql.Date(fechaIngreso.getTime()), new java.sql.Date(fechaEgreso.getTime()));
    }
}
