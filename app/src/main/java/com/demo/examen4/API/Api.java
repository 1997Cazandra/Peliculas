package com.demo.examen4.API;

import com.demo.examen4.entity.Gato;
import com.google.gson.JsonObject;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface Api {

    @GET("gato")
    Call<List<Gato>> ListaGato();

    @GET("gato/{id}")
    Call<Gato> obtenerDetallesGato(@Path("id") int id);

    @POST("gato/{id}/favorito")
    Call<Void> marcarGatoComoFavorito(@Path("id") int id, @Body JsonObject favoritoJson);

    @PUT("gato/{id}")
    Call<Void> actualizarGatoFavorito(@Path("id") int id, @Body JsonObject gatoJson);

    @GET("gato/favoritos")
    Call<List<Gato>> obtenerGatosFavoritos();

}
