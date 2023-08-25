package com.demo.examen4.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteRetrofit {

    private static Retrofit retrofit = null;

    public static Retrofit getClienteRetrofit(String urlServicio){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(urlServicio) // o pegamos la url del servicio
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //
        return retrofit;
    }
}
