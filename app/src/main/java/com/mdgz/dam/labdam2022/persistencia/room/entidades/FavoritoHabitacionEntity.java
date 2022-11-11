package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "favorito_habitacion",
        foreignKeys = {
        @ForeignKey(entity = HabitacionEntity.class, parentColumns = "id", childColumns = "habitacionID"),
                @ForeignKey(entity = UsuarioEntity.class, parentColumns = "id", childColumns = "usuarioID"),
        },
        indices = {@Index(value = {"habitacionID"}),@Index(value = {"usuarioID"})})
public class FavoritoHabitacionEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private UUID id;
    private UUID habitacionID;
    private UUID usuarioID; // raro

    public FavoritoHabitacionEntity(@NonNull UUID id, UUID habitacionID, UUID usuarioID) {
        this.id = id;
        this.habitacionID = habitacionID;
        this.usuarioID = usuarioID;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull UUID id) {
        this.id = id;
    }

    public UUID getHabitacionID() {
        return habitacionID;
    }

    public void setHabitacionID(UUID habitacionID) {
        this.habitacionID = habitacionID;
    }

    public UUID getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(UUID usuarioID) {
        this.usuarioID = usuarioID;
    }
}
