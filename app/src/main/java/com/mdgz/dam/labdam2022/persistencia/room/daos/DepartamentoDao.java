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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarDepartamento(DepartamentoEntity departamento);

    @Insert
    void insertarTodos(List<DepartamentoEntity> departamentos);

    @Query("SELECT * FROM Departamento")
    List<DepartamentoEntity> recuperarDepartamentos();

    //Buscar por id
    @Query("SELECT * FROM Departamento WHERE idDepartamento = :id")
    DepartamentoEntity getDepartamentoPorId(UUID id);

}
