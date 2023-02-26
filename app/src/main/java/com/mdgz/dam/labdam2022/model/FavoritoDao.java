package com.mdgz.dam.labdam2022.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoritoDao {
    @Insert
    void insert(Favorito favorito);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Favorito> favoritos);

    @Query("SELECT * FROM favorito")
    List<Favorito> obtenerFavoritos();
}
