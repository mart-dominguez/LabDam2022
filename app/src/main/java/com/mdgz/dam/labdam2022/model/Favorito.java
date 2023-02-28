package com.mdgz.dam.labdam2022.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.mdgz.dam.labdam2022.database.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.database.FavoritoEntity;
import com.mdgz.dam.labdam2022.database.room.AlojamientoRoomDataSource;

import java.util.List;
import java.util.UUID;

//@Entity(foreignKeys = {@ForeignKey(entity = Departamento.class, parentColumns = "id", childColumns = "departamentoId"),
 //       @ForeignKey(entity = Habitacion.class, parentColumns = "id", childColumns = "habitacionId")})
public class Favorito {
    public Favorito() {
    }

    public FavoritoEntity toEntity() {
        UUID alojamientoIdPasar;

        return new FavoritoEntity(alojamiento.getId(), UUID.randomUUID()); //Nosotros no guardabamos el usuarioId
    }

    public Favorito(UUID alojamientoId) {
        this.alojamientoId = alojamientoId;
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

   // @PrimaryKey
   // @NonNull
    @Expose
    UUID id;
    @Expose
    UUID habitacionId;
    @Expose
    UUID departamentoId;

    UUID alojamientoId;
   // @Ignore
    Alojamiento alojamiento;

   // @NonNull

    public void transformarAAlojamiento(Context context) {
        AlojamientoRoomDataSource alojamientoRoomDataSource = new AlojamientoRoomDataSource(context);

        AlojamientoDataSource.RecuperarAlojamientoCallback callback = (AlojamientoDataSource.RecuperarAlojamientoCallback) (exito, resultados) -> {
            this.alojamiento = resultados.get(0);
        };

        alojamientoRoomDataSource.recuperarAlojamientoId(callback, alojamientoId);
    }

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
