package com.mdgz.dam.labdam2022.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.UUID;

@Dao
public interface HabitacionDao {
    @Insert
    void insert(Habitacion habitacion);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Habitacion> habitaciones);

    @Query("SELECT * FROM Habitacion")
    List<Habitacion> obtenerHabitaciones();

    @Query("Select * FROM Habitacion WHERE id = :id")
    Habitacion obtenerHabitacion(UUID id);
}
