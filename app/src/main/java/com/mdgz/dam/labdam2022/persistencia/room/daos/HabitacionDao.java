package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HabitacionEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface HabitacionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarHabitacion(HabitacionEntity habitacion);

    @Insert
    void insertarTodos(List<HabitacionEntity> habitaciones);

    @Query("SELECT * FROM Habitacion")
    List<HabitacionEntity> recuperarHabitaciones();

    //Buscar por id
    @Query("SELECT * FROM Habitacion WHERE idHabitacion = :id")
    HabitacionEntity getHabitacionPorId(UUID id);
    
}
