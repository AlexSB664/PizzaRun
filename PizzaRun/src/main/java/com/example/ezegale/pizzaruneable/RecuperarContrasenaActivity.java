package com.example.ezegale.pizzaruneable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecuperarContrasenaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);
        Button NewContra = (Button) findViewById(R.id.btn_correo);
        NewContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Correo = (EditText) findViewById(R.id.inp_recContra);

                String EML;
                EML = Correo.getText().toString();
                if (EML.equals("")) {
                    Toast.makeText(RecuperarContrasenaActivity.this, "Escriba la dirección de correo electronico", Toast.LENGTH_LONG).show();

                } else {

                    try {
                        URL url = new URL("https://pizzarun-17.000webhostapp.com/cambioPassword.php?Eml=" +
                                EML);

                        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                        try {
                            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                                String linea = reader.readLine();
                                if (linea.equals("NO")) {
                                    Toast.makeText(RecuperarContrasenaActivity.this, "No se encontro cuenta asociada con el correo", Toast.LENGTH_LONG).show();
                                } else {
                                    Intent i = new Intent(v.getContext(), LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            } else {
                                Toast.makeText(RecuperarContrasenaActivity.this, "Error de conexión", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {

                        } finally {
                            conexion.disconnect();
                        }
                    } catch (IOException e) {

                    }
                }

            }
        });
    }
}
