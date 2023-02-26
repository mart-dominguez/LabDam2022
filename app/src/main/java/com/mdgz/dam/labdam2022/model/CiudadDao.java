package com.mdgz.dam.labdam2022.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.UUID;

@Dao
public interface CiudadDao {
    @Insert
    void insert(Ciudad ciudad);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Ciudad> ciudades);

    @Query("SELECT * FROM ciudad")
    List<Ciudad> obtenerCiudades();

    @Query("Select * FROM ciudad WHERE id = :id")
    Ciudad obtenerCiudad(Integer id);
}
