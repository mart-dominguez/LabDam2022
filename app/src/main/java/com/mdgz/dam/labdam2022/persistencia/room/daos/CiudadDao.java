package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.CiudadEntity;

import java.util.List;
@Dao
public interface CiudadDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertar(CiudadEntity ciudad);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertarTodos(List<CiudadEntity> ciudades);

    @Query("SELECT * FROM Ciudad")
    List<CiudadEntity> getTodas();

    //Buscar por id
    @Query("SELECT * FROM Ciudad WHERE id = :id")
    CiudadEntity getByID(Integer id);
    
}
