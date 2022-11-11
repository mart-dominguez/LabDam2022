package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "reserva_departamento",
        foreignKeys = {
                @ForeignKey(entity = DepartamentoEntity.class, parentColumns = "id", childColumns = "departamentoID"),
                @ForeignKey(entity = UsuarioEntity.class, parentColumns = "id", childColumns = "usuarioID")},
        indices = {@Index(value = {"departamentoID"}),@Index(value = {"usuarioID"})})
public class ReservaDepartamentoEntity
{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private UUID id;
    private UUID departamentoID;     //M..0.1
    private UUID usuarioID;         //M..0.1
    private Date fechaIngreso;
    private Date fechaSalida;

    public ReservaDepartamentoEntity(@NonNull UUID id, UUID departamentoID, UUID usuarioID, Date fechaIngreso, Date fechaSalida) {
        this.id = id;
        this.departamentoID = departamentoID;
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

    public UUID getDepartamentoID() {
        return departamentoID;
    }

    public void setDepartamentoID(UUID departamentoID) {
        this.departamentoID = departamentoID;
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
