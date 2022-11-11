package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.DepartamentoEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface DepartamentoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertar(DepartamentoEntity departamento);

    @Insert
    void insertarTodos(List<DepartamentoEntity> departamentos);

    @Query("SELECT * FROM Departamento")
    List<DepartamentoEntity> getTodos();

    //Buscar por id
    @Query("SELECT * FROM Departamento WHERE id = :id")
    DepartamentoEntity getByID(UUID id);

}
