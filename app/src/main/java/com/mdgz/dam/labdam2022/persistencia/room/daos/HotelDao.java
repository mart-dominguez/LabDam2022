package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Hotel;

import java.util.List;
@Dao
public interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarHotel(Hotel hotel);

    @Insert
    void insertarTodos(List<Hotel> hoteles);

    @Query("SELECT * FROM Hotel")
    List<Hotel> recuperarHoteles();

    //Buscar por id
    @Query("SELECT * FROM Hotel WHERE id = :id")
    Hotel getHotelPorId(Integer id);
    
}
