package com.mdgz.dam.labdam2022.persistencia.retrofit.daos;

import com.mdgz.dam.labdam2022.persistencia.retrofit.entidades.ReservaEntity;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReservaDao {

    @GET("reserva")
    Call<List<ReservaEntity>> getAll();

    @GET("reserva")
    Call<List<ReservaEntity>> getFromUser(@Query("usuarioId") UUID idUsuario);

    @POST("reserva") //Devuelve el status
    Call<ReservaEntity> insertar(@Body ReservaEntity reservaEntity);

    @DELETE("reserva")
    /*
        La pegada a la url con DELETE devuelve: "Tu favorito ha sido elimidado" (asi literal).
        Por eso uso ResponseBody (de la clase okhttp) como tipo de devolucion.
        Asi Gson puede convertir la devolucion a una clase, y en el callback no da Failure.
        https://stackoverflow.com/questions/32617770/how-to-get-response-as-string-using-retrofit-without-using-gson-or-any-other-lib
     */
    Call<ResponseBody> eliminar(@Query("alojamientoId") UUID idAlojamiento);
}
