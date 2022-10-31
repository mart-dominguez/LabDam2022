package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;
@Dao
public interface CiudadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarCiudad(Ciudad ciudad);

    @Insert
    void insertarTodos(List<Ciudad> ciudades);

    @Query("SELECT * FROM Ciudad")
    List<Ciudad> recuperarCiudades();

    //Buscar por id
    @Query("SELECT * FROM Ciudad WHERE id = :id")
    Ciudad getCiudadPorId(Integer id);
    
}
