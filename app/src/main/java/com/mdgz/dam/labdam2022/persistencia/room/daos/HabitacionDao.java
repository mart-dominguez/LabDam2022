package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Habitacion;

import java.util.List;
import java.util.UUID;

@Dao
public interface HabitacionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarHabitacion(Habitacion habitacion);

    @Insert
    void insertarTodos(List<Habitacion> habitaciones);

    @Query("SELECT * FROM Habitacion")
    List<Habitacion> recuperarHabitaciones();

    //Buscar por id
    @Query("SELECT * FROM Habitacion WHERE id = :id")
    Habitacion getHabitacionPorId(UUID id);
    
}
