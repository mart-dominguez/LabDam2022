package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoDepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoHabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaDepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.ReservaHabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UsuarioEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Dao
public interface ReservaDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertar(ReservaHabitacionEntity reserva);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertar(ReservaDepartamentoEntity reserva);

    @Query("SELECT * FROM reserva_habitacion")
    List<ReservaHabitacionEntity> getHabitaciones();

    @Query("SELECT * FROM reserva_departamento")
    List<ReservaDepartamentoEntity> getDepartamentos();

    @Delete
    void eliminar(ReservaHabitacionEntity reserva);

    @Delete
    void eliminar(ReservaDepartamentoEntity reserva);

    @Query("SELECT * FROM usuario " +
            "JOIN reserva_habitacion ON usuario.id = reserva_habitacion.usuarioID")
    Map<UsuarioEntity,List<ReservaHabitacionEntity>> habitacionesFromUsuario();

    @Query("SELECT * FROM usuario " +
            "JOIN reserva_departamento ON usuario.id = reserva_departamento.usuarioID")
    Map<UsuarioEntity,List<ReservaDepartamentoEntity>> departamentosFromUsuario();

    //Buscar por id
    @Query("SELECT * FROM reserva_habitacion WHERE id = :id")
    ReservaHabitacionEntity getHabitacionByID(UUID id);

    @Query("SELECT * FROM reserva_habitacion WHERE id = :id")
    ReservaDepartamentoEntity getDepartamentoByID(UUID id);

}
