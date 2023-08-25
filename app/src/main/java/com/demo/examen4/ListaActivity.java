package com.demo.examen4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.demo.examen4.API.Api;
import com.demo.examen4.API.ClienteRetrofit;
import com.demo.examen4.API.Constantes;
import com.demo.examen4.Adapters.GatoAdapter;
import com.demo.examen4.databinding.ActivityListaBinding;
import com.demo.examen4.entity.Gato;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Gato> listaGatos = new ArrayList<>();
    private GatoAdapter gatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        recyclerView = findViewById(R.id.rvLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gatoAdapter = new GatoAdapter(listaGatos);

        recyclerView.setAdapter(gatoAdapter);

        gatoAdapter.setOnItemClickListener(new GatoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Gato gato, int position) {
                // Obtener el ID del gato
                int gatoId = gato.getId();

                // Abrir DetallesActivity y pasar el ID del gato
                Intent intent = new Intent(ListaActivity.this, DetallesActivity.class);
                intent.putExtra("gatoId", gatoId);
                startActivity(intent);
            }
        });

        cargarDatos();
    }

    private void cargarDatos() {
        Api api = ClienteRetrofit.getClienteRetrofit(Constantes.ServicioBD).create(Api.class);
        Call<List<Gato>> call = api.ListaGato();

        call.enqueue(new Callback<List<Gato>>() {
            @Override
            public void onResponse(Call<List<Gato>> call, Response<List<Gato>> response) {
                if (response.isSuccessful()) {
                    listaGatos.clear();
                    listaGatos.addAll(response.body());
                    gatoAdapter.notifyDataSetChanged();
                } else {
                    // Manejo de error en caso de respuesta no exitosa
                }
            }

            @Override
            public void onFailure(Call<List<Gato>> call, Throwable t) {
                // Manejo de error en caso de falla
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = null;
        if (item.getItemId() == R.id.menu_favoritos){
            i = new Intent(ListaActivity.this, MisFavoritosActivity.class);
        }
        else if (item.getItemId() == R.id.menu_cerrarsesion) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Cerrar sesión");
            builder.setMessage("¿Estás seguro de que deseas cerrar sesión?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Si el usuario hace clic en "Sí", redirige a LoginActivity
                    Intent intent = new Intent(ListaActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Cierra la actividad actual para evitar regresar a ella
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Si el usuario hace clic en "No", simplemente cierra el cuadro de diálogo
                    dialogInterface.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

        if (i != null){
            startActivity(i);
        }

        return  true;
    }




}
