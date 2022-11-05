package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Reserva;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface ReservaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarReserva(ReservaEntity Reserva);

    @Query("SELECT * FROM Reserva")
    List<ReservaEntity> recuperarReservas();

    //Buscar por id
    @Query("SELECT * FROM Reserva WHERE id = :id")
    ReservaEntity getReservaPorId(UUID id);

}
