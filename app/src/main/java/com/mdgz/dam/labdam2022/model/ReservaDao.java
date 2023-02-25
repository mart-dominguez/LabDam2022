package com.mdgz.dam.labdam2022.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReservaDao {
    @Insert
    void insert(Reserva reserva);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Reserva> reservas);

    @Query("SELECT * FROM reserva")
    List<Reserva> obtenerReservas();
}
