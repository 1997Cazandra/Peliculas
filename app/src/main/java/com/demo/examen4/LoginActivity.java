package com.demo.examen4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.demo.examen4.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        setContentView(vista);

        binding.tiedtUserLogin.requestFocus();
        binding.btnIngresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLogin();
            }
        });


    }

    void validarLogin() {
        String user = binding.tiedtUserLogin.getText().toString();
        String password = binding.tiedtPasswordLogin.getText().toString();

        //Solo si empezamos a clasificar vistas xd
        if (user.equals("ejemploe@idat.com.pe") && password.equals("Peru123")) {
            // Credenciales de administrador, dirigir a una actividad de administrador
            Intent wuu = new Intent(LoginActivity.this, ListaActivity.class);
            startActivity(wuu);
            limpiardata();
            return; // Salir del método, no necesitas hacer la validación normal
        }

        if (user.equals("ejemploe@idat.com.pe")){
            Toast.makeText(this, "Password Incorrect", Toast.LENGTH_SHORT).show();
            binding.tiedtPasswordLogin.requestFocus();
        } else if(password.equals("Peru123")){
            Toast.makeText(this, "User Incorrect", Toast.LENGTH_SHORT).show();
            binding.tiedtUserLogin.requestFocus();
        } else {
            Toast.makeText(this, "Credenciales erroneas vuelve a intentar", Toast.LENGTH_SHORT).show();
            binding.tiedtUserLogin.requestFocus();
        }

    }

    void limpiardata(){
        binding.tiedtUserLogin.setText("");
        binding.tiedtPasswordLogin.setText("");
        binding.tiedtUserLogin.requestFocus();
    }



}