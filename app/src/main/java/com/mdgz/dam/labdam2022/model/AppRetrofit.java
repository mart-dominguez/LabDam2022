package com.mdgz.dam.labdam2022.model;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRetrofit {

    private static Retrofit retrofit;
    private static AppRetrofit INSTANCE;

    public synchronized static AppRetrofit getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new AppRetrofit();
        }
        return INSTANCE;
    }

    public AppRetrofit() {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm")
                .registerTypeAdapter(Date.class, new DateSerializer())
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dam-recordatorio-favoritos-api.duckdns.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder().
                        addInterceptor((Interceptor.Chain c)-> {
                            Request originalRequest = c.request();
                            Request.Builder request = originalRequest.newBuilder().addHeader("Authorization", "BASIC Z3J1cG9BRlQ6Z3J1cG9BRlQ=").method(originalRequest.method(), originalRequest.body());
                            return c.proceed(request.build());
                        }).build())
                .build();
    }

    public FavoritoRest favoritoRest() {
        return retrofit.create(FavoritoRest.class);
    }

    public ReservaRest reservaRest() {
        return retrofit.create(ReservaRest.class);
    }

}
