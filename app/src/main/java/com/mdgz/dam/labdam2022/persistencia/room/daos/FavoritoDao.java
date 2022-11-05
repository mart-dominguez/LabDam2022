package com.mdgz.dam.labdam2022.persistencia.room.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoEntity;

import java.util.List;
import java.util.UUID;

@Dao
public interface FavoritoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarFavorito(FavoritoEntity favorito);

    @Query("SELECT * FROM Favorito")
    List<FavoritoEntity> recuperarFavoritos();

    @Delete
    void eliminarFavorito(FavoritoEntity favorito);

    //Buscar por id
    @Query("SELECT * FROM Favorito WHERE id = :id")
    FavoritoEntity getFavoritoPorId(UUID id);
}
