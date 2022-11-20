package com.mdgz.dam.labdam2022.persistencia.room.impl;

import android.content.Context;
import android.util.Log;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.interfaces.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoDepartamentoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoHabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.FavoritoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FavoritoRoomDataSource implements FavoritoDataSource {

    private FavoritoDao favoritoDao;
    private AlojamientoRoomDataSource alojamientoRoomDataSource;
    private UsuarioRoomDataSource usuarioRoomDataSource;

    //private Context context;

    public FavoritoRoomDataSource(Context ctx)
    {
        BaseDeDatos bd = BaseDeDatos.getInstance(ctx);
        favoritoDao = bd.favoritoDao();
        alojamientoRoomDataSource = new AlojamientoRoomDataSource(ctx);
        usuarioRoomDataSource = new UsuarioRoomDataSource(ctx);
        //context = ctx;
    }

    @Override
    public void guardar(Favorito entidad, GuardarCallback callback)
    {
        try
        {
            usuarioRoomDataSource.guardar(entidad.getUsuario(),exito -> {});
            if(entidad.getAlojamiento().getClass() == Departamento.class)
            {
                alojamientoRoomDataSource.guardar((Departamento) entidad.getAlojamiento(), exito -> {
                });
                favoritoDao.insertar(FavoritoMapper.toDepartamentoEntity(entidad));
            }
            else
            {
                alojamientoRoomDataSource.guardar((Habitacion) entidad.getAlojamiento(), exito -> {
                });
                favoritoDao.insertar(FavoritoMapper.toHabitacionEntity(entidad));
            }
            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }
    }

    @Override
    public void getTodos(RecuperarCallback callback)
    {
        try {
            List<FavoritoHabitacionEntity> entities1 = favoritoDao.getHabitaciones();
            List<FavoritoDepartamentoEntity> entities2 = favoritoDao.getDepartamentos();
            List<Favorito> favoritos = new ArrayList<>();

            for (FavoritoHabitacionEntity entity : entities1) {
                Favorito favorito = FavoritoMapper.toModel(entity);
                favoritos.add(favorito);
            }

            for (FavoritoDepartamentoEntity entity : entities2) {
                Favorito favorito = FavoritoMapper.toModel(entity);
                favoritos.add(favorito);
            }
            callback.resultado(true,favoritos);
        } catch (Exception e)
        {
            callback.resultado(false,null);
        }

    }

    @Override
    public void getById(UUID id, GetByIDCallback callback)
    {
        try
        {
            FavoritoHabitacionEntity entity1 = favoritoDao.getHabitacionByID(id);
            if(entity1 == null)
            {
                FavoritoDepartamentoEntity entity2 = favoritoDao.getDepartamentoByID(id);
                Favorito favorito = FavoritoMapper.toModel(entity2);
                callback.resultado(true,favorito);
            } else
            {
                Favorito favorito = FavoritoMapper.toModel(entity1);
                callback.resultado(true,favorito);
            }
        } catch (Exception e)
        {
            callback.resultado(false,null);
        }
    }

    @Override
    public void getFromUser(UUID id, RecuperarCallback callback) {
        // No implementado
        Log.e("Error:","Metodo getFromUser del FavoritoRoomDataSource no implementado.");
    }

    @Override
    public void eliminar(Favorito favorito, EliminarCallback callback)
    {
        try
        {
            if(favorito.getAlojamiento().getClass() == Departamento.class)
            {
                FavoritoDepartamentoEntity entity = FavoritoMapper.toDepartamentoEntity(favorito);
                favoritoDao.eliminar(entity);
                callback.resultado(true);
            } else
            {
                FavoritoHabitacionEntity entity = FavoritoMapper.toHabitacionEntity(favorito);
                favoritoDao.eliminar(entity);
                callback.resultado(true);
            }
        } catch (Exception e)
        {
            callback.resultado(false);
        }

    }

}
