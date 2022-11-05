package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Hotel;

import java.util.UUID;

@Entity(tableName = "Habitacion",
        foreignKeys = { @ForeignKey(entity = HotelEntity.class, parentColumns = "id", childColumns = "hotelID"),
                        @ForeignKey(entity = AlojamientoEntity.class, parentColumns = "id", childColumns = "idHabitacion")})

public class HabitacionEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idHabitacion")
    private UUID idHabitacion;
    private Integer camasIndividuales;
    private Integer camasMatrimoniales;
    private Boolean tieneEstacionamiento;
    private Integer hotelID;

    public HabitacionEntity(@NonNull UUID idHabitacion, Integer camasIndividuales, Integer camasMatrimoniales, Boolean tieneEstacionamiento, Integer hotelID) {
        this.idHabitacion = idHabitacion;
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotelID = hotelID;
    }

    @NonNull
    public UUID getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(@NonNull UUID idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public Integer getCamasIndividuales() {
        return camasIndividuales;
    }

    public void setCamasIndividuales(Integer camasIndividuales) {
        this.camasIndividuales = camasIndividuales;
    }

    public Integer getCamasMatrimoniales() {
        return camasMatrimoniales;
    }

    public void setCamasMatrimoniales(Integer camasMatrimoniales) {
        this.camasMatrimoniales = camasMatrimoniales;
    }

    public Boolean getTieneEstacionamiento() {
        return tieneEstacionamiento;
    }

    public void setTieneEstacionamiento(Boolean tieneEstacionamiento) {
        this.tieneEstacionamiento = tieneEstacionamiento;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }
}
