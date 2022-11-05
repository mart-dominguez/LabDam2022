package com.mdgz.dam.labdam2022.persistencia.room;

import android.content.Context;

import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.persistencia.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;
import com.mdgz.dam.labdam2022.persistencia.room.daos.AlojamientoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.DepartamentoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.room.daos.HabitacionDao;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.AlojamientoEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoEntity;
import com.mdgz.dam.labdam2022.persistencia.enumerados.TipoAlojamiento;
import com.mdgz.dam.labdam2022.persistencia.room.mapeadores.FavoritoMapper;

import java.util.ArrayList;
import java.util.List;

public class FavoritoRoomDataSource implements FavoritoDataSource {

    private FavoritoDao favoritoDao;
    private DepartamentoDao departamentoDao;
    private HabitacionDao habitacionDao;
    private AlojamientoDao alojamientoDao;

    private Context context;

    public FavoritoRoomDataSource(Context ctx){
        BaseDeDatos bd = BaseDeDatos.getInstance(ctx);
        favoritoDao = bd.favoritoDao();
        departamentoDao = bd.departamentoDao();
        habitacionDao = bd.habitacionDao();
        alojamientoDao = bd.alojamientoDao();
        context = ctx;
    }

    @Override
    public void guardarFavorito(Favorito entidad, GuardarFavoritoCallback callback) {
        try{
            favoritoDao.insertarFavorito(FavoritoMapper.toEntity(entidad));
            callback.resultado(true);
        }catch (Exception e){
            callback.resultado(false);
        }
    }

    @Override
    public void recuperarFavoritos(RecuperarFavoritosCallback callback) {

        AlojamientoRoomDataSource alojamientoRoomDataSource = new AlojamientoRoomDataSource(context);

        try {
            AlojamientoEntity alojamientoEntity;

            List<Favorito> favoritos = new ArrayList<>();
            Favorito favorito;

            List<FavoritoEntity> favoritoEntities = favoritoDao.recuperarFavoritos();

            for (FavoritoEntity fav: favoritoEntities) {
                favorito = FavoritoMapper.toFavorito(fav);

                alojamientoEntity = alojamientoDao.getAlojamientoPorId(fav.getAlojamientoID());

                //Si es un favorito de un departamento
                if(alojamientoEntity.getTipo().equals(TipoAlojamiento.DEPARTAMENTO.name())){
                    Departamento dep = alojamientoRoomDataSource.armarDepartamento(departamentoDao.getDepartamentoPorId(fav.getAlojamientoID()));
                    favorito.setAlojamiento(dep);
                }
                //Si es un favorito de una habitacion
                else{
                    Habitacion hab = alojamientoRoomDataSource.armarHabitacion(habitacionDao.getHabitacionPorId(fav.getAlojamientoID()));
                    favorito.setAlojamiento(hab);
                }

                favoritos.add(favorito);
            }

            callback.resultado(true, favoritos);

        }catch (Exception e){
            callback.resultado(false, null);
        }

    }
}
