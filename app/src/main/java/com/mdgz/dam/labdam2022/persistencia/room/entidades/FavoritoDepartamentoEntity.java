package com.mdgz.dam.labdam2022.persistencia.room.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "favorito_departamento",
        foreignKeys = {
                @ForeignKey(entity = DepartamentoEntity.class, parentColumns = "id", childColumns = "departamentoID"),
                @ForeignKey(entity = UsuarioEntity.class, parentColumns = "id", childColumns = "usuarioID"),
        },
        indices = {@Index(value = {"departamentoID"}),@Index(value = {"usuarioID"})})
public class FavoritoDepartamentoEntity
{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private UUID id;
    private UUID departamentoID;
    private UUID usuarioID; // raro

    public FavoritoDepartamentoEntity(@NonNull UUID id, UUID departamentoID, UUID usuarioID)
    {
        this.id = id;
        this.departamentoID = departamentoID;
        this.usuarioID = usuarioID;
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
}
