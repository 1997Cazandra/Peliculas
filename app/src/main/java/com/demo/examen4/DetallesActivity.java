package com.demo.examen4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.examen4.API.Api;
import com.demo.examen4.API.ClienteRetrofit;
import com.demo.examen4.API.Constantes;
import com.demo.examen4.databinding.ActivityDetallesBinding;
import com.demo.examen4.databinding.ActivityListaBinding;
import com.demo.examen4.entity.Gato;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallesActivity extends AppCompatActivity {

    ActivityDetallesBinding binding;

    private ImageView imgDetalle;
    private TextView txtNombreDetalle, txtRazaDetalle, txtEdadDetalle, txtComidaDetalle, txtPaisDetalle, txtFavoritoDetalle;

    private Button btnFavorito;
    int gatoId;
    String urlimagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetallesBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        setContentView(vista);

        // Inicializa las vistas
        imgDetalle = findViewById(R.id.imgDetalle);
        txtNombreDetalle = findViewById(R.id.txtNombreDetalle);
        txtRazaDetalle = findViewById(R.id.txtRazaDetalle);
        txtEdadDetalle = findViewById(R.id.txtEdadDetalle);
        txtComidaDetalle = findViewById(R.id.txtComidaDetalle);
        txtPaisDetalle = findViewById(R.id.txtPaisDetalle);
        txtFavoritoDetalle = findViewById(R.id.txtFavoritoDetalle);
        // Obtener el ID del gato de los extras
        Intent intent = getIntent();
        gatoId = intent.getIntExtra("gatoId", -1);

        //binding.txtNombreDetalle.setText(String.valueOf(gatoId));

        // Hacer la llamada a la API para obtener los detalles del gato
        Api api = ClienteRetrofit.getClienteRetrofit(Constantes.ServicioBD).create(Api.class);
        Call<Gato> call = api.obtenerDetallesGato(gatoId);

        call.enqueue(new Callback<Gato>() {
            @Override
            public void onResponse(Call<Gato> call, Response<Gato> response) {
                if (response.isSuccessful()) {
                    Gato gato = response.body();
                    mostrarDetallesGato(gato);
                } else {
                    // Manejo de error en caso de respuesta no exitosa
                }
            }

            @Override
            public void onFailure(Call<Gato> call, Throwable t) {
                // Manejo de error en caso de falla
            }
        });


        btnFavorito = findViewById(R.id.btnFavorito);
        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarFavorito(gatoId);
            }
        });

    }

    private void mostrarDetallesGato(Gato gato) {
        // Mostrar los detalles del gato en las vistas
        Picasso.get().load(gato.getImagen()).into(imgDetalle);
        txtNombreDetalle.setText(gato.getNombre());
        txtRazaDetalle.setText(gato.getRaza());
        txtEdadDetalle.setText(gato.getEdad());
        txtComidaDetalle.setText(gato.getComida());
        txtPaisDetalle.setText(gato.getPais());
        txtFavoritoDetalle.setText(gato.getFavorito());
        urlimagen = gato.getImagen();
    }

    private void enviarFavorito(int gatoId) {
        Api api = ClienteRetrofit.getClienteRetrofit(Constantes.ServicioBD).create(Api.class);

        // Crear el objeto JSON con los campos a actualizar (incluido "favorito")
        JsonObject gatoJson = new JsonObject();
        gatoJson.addProperty("imagen", urlimagen);
        gatoJson.addProperty("nombre", txtNombreDetalle.getText().toString());
        gatoJson.addProperty("raza", txtRazaDetalle.getText().toString());
        gatoJson.addProperty("edad", txtEdadDetalle.getText().toString());
        gatoJson.addProperty("comida", txtComidaDetalle.getText().toString());
        gatoJson.addProperty("pais", txtPaisDetalle.getText().toString());
        gatoJson.addProperty("favorito", "Sí");


        Call<Void> call = api.actualizarGatoFavorito(gatoId, gatoJson);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Intent i = new Intent(DetallesActivity.this,ListaActivity.class);
                    Toast.makeText(DetallesActivity.this, "Agregado a Favoritos!", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                } else {
                    // Manejar el error en caso de respuesta no exitosa
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar el error en caso de falla
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
            i = new Intent(DetallesActivity.this, MisFavoritosActivity.class);
        }
        else if (item.getItemId() == R.id.menu_cerrarsesion) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Cerrar sesión");
            builder.setMessage("¿Estás seguro de que deseas cerrar sesión?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Si el usuario hace clic en "Sí", redirige a LoginActivity
                    Intent intent = new Intent(DetallesActivity.this, LoginActivity.class);
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