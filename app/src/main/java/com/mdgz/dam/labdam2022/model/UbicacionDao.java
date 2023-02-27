package com.mdgz.dam.labdam2022.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.UUID;

@Dao
public interface UbicacionDao {
    @Insert
    void insert(Ubicacion ubicacion);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Ubicacion> ubicaciones);

    @Query("SELECT * FROM Ubicacion")
    List<Ubicacion> obtenerUbicaciones();

    @Query("Select * FROM Ubicacion WHERE id = :id")
    Ubicacion obtenerUbicacion(UUID id);

    @Query("Select * FROM Ubicacion WHERE lat = :lat AND lng = :lng")
    Ubicacion obtenerUbicacion(double lat, double lng);


}
