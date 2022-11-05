package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entidades.AlojamientoEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface AlojamientoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarAlojamiento(AlojamientoEntity Alojamiento);

    @Insert
    void insertarTodos(List<AlojamientoEntity> Alojamientos);

    @Query("SELECT * FROM Alojamiento")
    List<AlojamientoEntity> recuperarAlojamientos();

    //Buscar por id
    @Query("SELECT * FROM Alojamiento WHERE id = :id")
    AlojamientoEntity getAlojamientoPorId(UUID id);
    
}
