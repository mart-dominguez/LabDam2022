package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.UUID;
@Entity(tableName = "Departamento",
        foreignKeys = { @ForeignKey(entity = UbicacionEntity.class, parentColumns = "id", childColumns = "ubicacionID"),
                        @ForeignKey(entity = AlojamientoEntity.class, parentColumns = "id", childColumns = "idDepartamento")})
public class DepartamentoEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idDepartamento")
    private UUID idDepartamento;
    private Boolean tieneWifi;
    private Double costoLimpieza;
    private Integer cantidadHabitaciones;
    private Integer ubicacionID;

    public DepartamentoEntity(@NonNull UUID idDepartamento, Boolean tieneWifi, Double costoLimpieza, Integer cantidadHabitaciones, Integer ubicacionID) {
        this.idDepartamento = idDepartamento;
        this.tieneWifi = tieneWifi;
        this.costoLimpieza = costoLimpieza;
        this.cantidadHabitaciones = cantidadHabitaciones;
        this.ubicacionID = ubicacionID;
    }

    @NonNull
    public UUID getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(@NonNull UUID idDepartamento) {
        this.idDepartamento = idDepartamento;
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
}
