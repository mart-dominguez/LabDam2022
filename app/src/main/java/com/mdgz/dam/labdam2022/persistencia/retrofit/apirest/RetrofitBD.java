package com.mdgz.dam.labdam2022.persistencia.retrofit.apirest;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mdgz.dam.labdam2022.persistencia.retrofit.daos.FavoritoDao;
import com.mdgz.dam.labdam2022.persistencia.retrofit.daos.ReservaDao;

import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBD {

    private static RetrofitBD INSTANCE;
    private Retrofit retrofit;
    private static final String AUTH = "Basic " + Base64.encodeToString(("alerod:ale1234").getBytes(), Base64.NO_WRAP);
    private static final String BASE_URL = "https://dam-recordatorio-favoritos-api.duckdns.org/";

    public RetrofitBD() {

        // Para la autenticacion
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor((Interceptor.Chain chain)->{
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("Authorization", AUTH)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);

                })
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .registerTypeAdapter(Date.class, new DateDeserializer()) // para cuando gson tenga que convertir de string a date
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

    }

    public synchronized static RetrofitBD getInstance()
    {
        return INSTANCE == null ? new RetrofitBD() : INSTANCE;
    }

    public FavoritoDao favoritoDao(){
        return retrofit.create(FavoritoDao.class);
    }
    public ReservaDao reservaDao(){
        return retrofit.create(ReservaDao.class);
    }

}
