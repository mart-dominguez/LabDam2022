package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mdgz.dam.labdam2022.persistencia.room.entidades.UsuarioEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertar(UsuarioEntity usuario);

    @Query("SELECT * FROM usuario")
    List<UsuarioEntity> getTodos();

    @Delete
    void eliminar(UsuarioEntity usuario);

    @Update
    void actualizar(UsuarioEntity usuario);

    @Query("SELECT * FROM usuario WHERE usuario.id = :id")
    UsuarioEntity getByID(UUID id);

}
