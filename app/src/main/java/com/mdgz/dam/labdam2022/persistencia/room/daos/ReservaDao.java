package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Reserva;

import java.util.List;
import java.util.UUID;

@Dao
public interface ReservaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarReserva(Reserva Reserva);

    @Query("SELECT * FROM Reserva")
    List<Reserva> recuperarReservas();

    //Buscar por id
    @Query("SELECT * FROM Reserva WHERE id = :id")
    Reserva getReservaPorId(UUID id);

}
