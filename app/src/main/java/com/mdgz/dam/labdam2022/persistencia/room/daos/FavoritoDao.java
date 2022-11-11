package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoDepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoHabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UsuarioEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Dao
public interface FavoritoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertar(FavoritoHabitacionEntity favorito);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertar(FavoritoDepartamentoEntity favorito);

    @Query("SELECT * FROM favorito_habitacion")
    List<FavoritoHabitacionEntity> getHabitaciones();

    @Query("SELECT * FROM favorito_departamento")
    List<FavoritoDepartamentoEntity> getDepartamentos();

    @Delete
    void eliminar(FavoritoHabitacionEntity favorito);

    @Delete
    void eliminar(FavoritoDepartamentoEntity favorito);

    //Buscar por id
    @Query("SELECT * FROM favorito_habitacion WHERE id = :id")
    FavoritoHabitacionEntity getHabitacionByID(UUID id);

    @Query("SELECT * FROM favorito_habitacion WHERE id = :id")
    FavoritoDepartamentoEntity getDepartamentoByID(UUID id);

    @Query("SELECT * FROM usuario " +
            "JOIN favorito_habitacion ON usuario.id = favorito_habitacion.usuarioID " +
            "WHERE usuario.id = :id")
    Map<UsuarioEntity,List<FavoritoHabitacionEntity>> habitacionesFromUsuario(UUID id);

    @Query("SELECT * FROM usuario " +
            "JOIN favorito_departamento ON usuario.id = favorito_departamento.usuarioID " +
            "WHERE usuario.id = :id")
    Map<UsuarioEntity,List<FavoritoDepartamentoEntity>> departamentosFromUsuario(UUID id);
}
