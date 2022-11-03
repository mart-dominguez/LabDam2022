package com.mdgz.dam.labdam2022.persistencia.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.CiudadDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HotelDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.UbicacionDao;

import java.util.List;

public class FavoritoRoomDataSource implements FavoritoDataSource {

    private FavoritoDao favoritoDao;
    private DepartamentoDao departamentoDao;
    private HabitacionDao habitacionDao;
    private CiudadDao ciudadDao;
    private HotelDao hotelDao;
    private UbicacionDao ubicacionDao;

    public FavoritoRoomDataSource(Context ctx){
        BaseDeDatos bd = BaseDeDatos.getInstance(ctx);
        favoritoDao = bd.favoritoDao();
        departamentoDao = bd.departamentoDao();
        habitacionDao = bd.habitacionDao();
        ciudadDao = bd.ciudadDao();
        hotelDao = bd.hotelDao();
        ubicacionDao = bd.ubicacionDao();
    }

    @Override
    public void guardarFavorito(Favorito entidad, GuardarFavoritoCallback callback) {
        try{
            favoritoDao.insertarFavorito(entidad);
            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }
    }

    @Override
    public void recuperarFavoritos(RecuperarFavoritosCallback callback) {

        try {
            List<Favorito> favoritos = favoritoDao.recuperarFavoritos();
            for (Favorito fav: favoritos) {
                //Si es un favorito de un departamento
                if(fav.getHabitacionID().equals(null)){
                    Departamento dep = departamentoDao.getDepartamentoPorId(fav.getDepartamentoID());
                    dep.setUbicacion(ubicacionDao.getUbicacionPorId(dep.getUbicacionID()));
                    dep.getUbicacion().setCiudad(ciudadDao.getCiudadPorId(dep.getUbicacion().getCiudadID()));
                    fav.setAlojamiento(dep);
                }else { //Es un favorito de habitacion
                    Habitacion hab = habitacionDao.getHabitacionPorId(fav.getHabitacionID());
                    hab.setHotel(hotelDao.getHotelPorId(hab.getHotelID()));
                    hab.getHotel().setUbicacion(ubicacionDao.getUbicacionPorId(hab.getHotel().getUbicacionID()));
                    hab.getUbicacion().setCiudad(ciudadDao.getCiudadPorId(hab.getUbicacion().getCiudadID()));
                    fav.setAlojamiento(hab);
                }
            }

            callback.resultado(true, favoritos);

        }catch (Exception e){
            callback.resultado(false, null);
        }

    }
}
