package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Ubicacion;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;
@Entity(tableName = "Departamento",
        foreignKeys = { @ForeignKey(entity = UbicacionEntity.class, parentColumns = "id", childColumns = "ubicacionID")
                        },
        indices = {@Index(value = {"ubicacionID"})})
public class DepartamentoEntity
{

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    private UUID id;
    @Embedded
    private AlojamientoEntity alojamiento;
    private Boolean tieneWifi;
    private Double costoLimpieza;
    private Integer cantidadHabitaciones;
    private Integer ubicacionID;

    public DepartamentoEntity(@NonNull UUID id, AlojamientoEntity alojamiento, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones, Integer ubicacionID)
    {
        this.id = id;
        this.alojamiento = alojamiento;
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacionID = ubicacionID;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public Boolean getTieneWifi() {
        return tieneWifi;
    }

    public void setTieneWifi(Boolean tieneWifi) {
        this.tieneWifi = tieneWifi;
    }

    public Double getCostoLimpieza() {
        return costoLimpieza;
    }

    public void setCostoLimpieza(Double costoLimpieza) {
        this.costoLimpieza = costoLimpieza;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public Integer getUbicacionID() {
        return ubicacionID;
    }

    public void setUbicacionID(Integer ubicacionID) {
        this.ubicacionID = ubicacionID;
    }

    public AlojamientoEntity getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(AlojamientoEntity alojamiento) {
        this.alojamiento = alojamiento;
    }
}
