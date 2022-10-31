package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Favorito;

import java.util.List;
@Dao
public interface FavoritoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarFavorito(Favorito favorito);

    @Query("SELECT * FROM Favorito")
    List<Favorito> recuperarFavoritos();

    @Delete
    void eliminarFavorito(Favorito favorito);

    //Buscar por id
    @Query("SELECT * FROM Favorito WHERE id = :id")
    Favorito getFavoritoPorId(Integer id);
}
