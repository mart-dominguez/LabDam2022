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

@Entity(tableName = "Reserva",
        foreignKeys = {@ForeignKey(entity = Departamento.class, parentColumns = "id", childColumns = "departamentoID"),
                       @ForeignKey(entity = Habitacion.class, parentColumns = "id", childColumns = "habitacionID")})
public class Reserva {

    @PrimaryKey//(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private UUID id;
    //private UUID alojamientoID;
    private UUID habitacionID;
    private UUID departamentoID;
    private UUID usuarioID; // raro
    private Date fechaIngreso;
    private Date fechaSalida;

    //private Instant fechaIngreso;
    //private Instant fechaEgreso;
    //private Boolean cancelada;
    //private Double monto;

    @Ignore
    private Alojamiento alojamiento;

    public Reserva(UUID id, UUID habitacionID, UUID departamentoID, UUID usuarioID, Date fechaIngreso, Date fechaSalida){
        this.id = id;
        this.habitacionID = habitacionID;
        this.departamentoID = departamentoID;
        this.usuarioID = usuarioID;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
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
