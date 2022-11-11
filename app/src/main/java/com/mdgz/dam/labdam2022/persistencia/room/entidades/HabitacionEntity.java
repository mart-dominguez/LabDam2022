package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.mdgz.dam.labdam2022.model.Hotel;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity(tableName = "Habitacion",
        foreignKeys = { @ForeignKey(entity = HotelEntity.class, parentColumns = "id", childColumns = "hotelID")
                        },
        indices = {@Index(value = {"hotelID"})})

public class HabitacionEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NotNull
    private UUID id;
    @Embedded
    private AlojamientoEntity alojamiento;
    private Integer camasIndividuales;
    private Integer camasMatrimoniales;
    private Boolean tieneEstacionamiento;
    private Integer hotelID;

    public HabitacionEntity(@NonNull UUID id, AlojamientoEntity alojamiento, Integer camasIndividuales, Integer camasMatrimoniales, Boolean tieneEstacionamiento, Integer hotelID) {
        this.id = id;
        this.alojamiento = alojamiento;
        this.camasIndividuales = camasIndividuales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.tieneEstacionamiento = tieneEstacionamiento;
        this.hotelID = hotelID;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id= id;
    }

    public Integer getCamasIndividuales() {
        return camasIndividuales;
    }

    public void setCamasIndividuales(Integer camasIndividuales) { this.camasIndividuales = camasIndividuales;}

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

    public AlojamientoEntity getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(AlojamientoEntity alojamiento) {
        this.alojamiento = alojamiento;
    }
}
