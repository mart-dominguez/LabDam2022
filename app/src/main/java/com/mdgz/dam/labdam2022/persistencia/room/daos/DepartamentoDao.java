package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Departamento;

import java.util.List;
@Dao
public interface DepartamentoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarDepartamento(Departamento departamento);

    @Insert
    void insertarTodos(List<Departamento> departamentos);

    @Query("SELECT * FROM Departamento")
    List<Departamento> recuperarDepartamentos();

    //Buscar por id
    @Query("SELECT * FROM Departamento WHERE id = :id")
    Departamento getDepartamentoPorId(Integer id);

}
