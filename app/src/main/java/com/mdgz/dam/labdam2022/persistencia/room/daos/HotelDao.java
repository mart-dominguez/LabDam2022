package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.HotelEntity;

import java.util.List;
@Dao
public interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertar(HotelEntity hotel);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertarTodos(List<HotelEntity> hoteles);

    @Query("SELECT * FROM Hotel")
    List<HotelEntity> getTodos();

    //Buscar por id
    @Query("SELECT * FROM Hotel WHERE id = :id")
    HotelEntity getByID(Integer id);
    
}
