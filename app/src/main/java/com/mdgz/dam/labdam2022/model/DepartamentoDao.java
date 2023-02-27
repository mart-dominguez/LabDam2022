package com.mdgz.dam.labdam2022.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
import java.util.UUID;

@Dao
public interface DepartamentoDao {
    @Insert
    void insert(Departamento departamento);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Departamento> departamentos);

    @Query("SELECT * FROM Departamento")
    List<Departamento> obtenerDepartamentos();

    @Query("SELECT * FROM Departamento WHERE id = :id")
    Departamento obtenerDepartamento(UUID id);
}
