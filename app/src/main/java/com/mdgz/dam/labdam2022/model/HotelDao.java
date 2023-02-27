package com.mdgz.dam.labdam2022.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.UUID;

@Dao
public interface HotelDao {
    @Insert
    void insert(Hotel hotel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Hotel> hoteles);

    @Query("SELECT * FROM Hotel")
    List<Hotel> obtenerHotel();

    @Query("Select * FROM Hotel WHERE id = :id")
    Hotel obtenerHotel(Integer id);
}
