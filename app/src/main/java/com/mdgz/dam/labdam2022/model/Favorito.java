package com.mdgz.dam.labdam2022.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(foreignKeys = {@ForeignKey(entity = Departamento.class, parentColumns = "id", childColumns = "departamentoId"),
        @ForeignKey(entity = Habitacion.class, parentColumns = "id", childColumns = "habitacionId")})
public class Favorito {
    public Favorito() {
    }

    public Favorito(Alojamiento alojamiento) {
        this.id = UUID.randomUUID();
        this.alojamiento = alojamiento;

        if (alojamiento instanceof Departamento) {
            this.departamentoId = alojamiento.getId();
        }
        else {
            this.habitacionId = alojamiento.getId();
        }
    }

    @PrimaryKey
    @NonNull
    UUID id;
    UUID habitacionId;
    UUID departamentoId;
    @Ignore
    Alojamiento alojamiento;

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public UUID getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(UUID habitacionId) {
        this.habitacionId = habitacionId;
    }

    public UUID getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(UUID departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }
}
