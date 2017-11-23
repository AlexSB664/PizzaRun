package com.example.ezegale.pizzaruneable;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        setContentView(R.layout.activity_registro);
        Button regtr = (Button) findViewById(R.id.btn_Registrarse);
        regtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.nameNewUsertxt);
                EditText usrnm = (EditText) findViewById(R.id.usernameNewUsertxt);
                EditText emil = (EditText) findViewById(R.id.emailNewUserTxt);
                EditText pswrd = (EditText) findViewById(R.id.passwordNewUserTxt);
                EditText nmro = (EditText) findViewById(R.id.numeroUserNameTxt);
                String nameURL = name.getText().toString().replace(" ", "%20");
                String userId = usrnm.getText().toString();
                String mail = emil.getText().toString();
                String psw = pswrd.getText().toString();
                String nroTel = nmro.getText().toString();
                boolean error = false;
                if (nameURL.equals(""))
                {
                    error = true;
                    name.setError("Campo Obligatorio");
                }
                if( userId.equals(""))
                {
                    error = true;
                    usrnm.setError("Campo Obligatorio");
                }
                if (mail.equals(""))
                {
                    error = true;
                    emil.setError("Campo Obligatorio");
                }
                if (psw.equals(""))
                {
                    error = true;
                    pswrd.setError("Campo Obligatorio");
                }
                if (error){
                    Toast.makeText(RegistroActivity.this, "Verifique los campos", Toast.LENGTH_LONG).show();
                }
                else {

                    try {
                        URL urlR = new URL("https://pizzarun-17.000webhostapp.com/nuevoUsuario.php?UrN=" + userId
                                + "&Eml=" + mail + "&PsW=" + psw + "&NmE=" + nameURL + "&NrO=" + nroTel);
                        HttpURLConnection conexionR = (HttpURLConnection) urlR.openConnection();
                        try {
                            if (conexionR.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(conexionR.getInputStream()));
                                String linea = reader.readLine();
                                if (linea.equals("EMAIL")) {
                                    Toast.makeText(RegistroActivity.this, "El correo ya pertenece a una cuenta", Toast.LENGTH_LONG).show();
                                } else if (linea.equals("USER")) {
                                    Toast.makeText(RegistroActivity.this, "Ya existe una cuenta registrada con ese usuario", Toast.LENGTH_LONG).show();
                                } else if (linea.equals("NUMERO")) {
                                    Toast.makeText(RegistroActivity.this, "Ya existe una cuenta registrada con ese número", Toast.LENGTH_LONG).show();
                                } else {
                                    error = false;
                                    Intent i = new Intent(view.getContext(), LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            } else {
                                Toast.makeText(RegistroActivity.this, "Compruebe su conexión", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(RegistroActivity.this, "Compruebe su conexión", Toast.LENGTH_LONG).show();
                        } finally {
                            conexionR.disconnect();
                        }
                    } catch (IOException e) {

                    }
                }

            }
        });
    }
}
