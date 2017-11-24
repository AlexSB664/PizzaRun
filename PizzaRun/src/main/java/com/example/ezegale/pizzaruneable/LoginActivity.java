package com.example.ezegale.pizzaruneable;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends AppCompatActivity {

    private static final String STRING_PREFERENCES = "ezegale.pizzaruneable";
    private static final String STRING_LINK= "NO";
    private URL ul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        Button btnlogin = (Button) findViewById(R.id.btn_Ingresar);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText user = (EditText) findViewById(R.id.idText_Usuario);
                EditText pwd = (EditText) findViewById(R.id.idText_Password);
                String UN;
                UN = user.getText().toString();
                String UP;
                UP = pwd.getText().toString();
                boolean error = false;
                if (UN.equals("")) {
                    error = true;
                    user.setError("Campo Obligatorio");
                }
                if(UP.equals("")){
                    error = true;
                    pwd.setError("campo obligatorio");
                }
                if (error){
                    Toast.makeText(LoginActivity.this, "Compruebe los campos", Toast.LENGTH_SHORT).show();
                }else {
                   try {
                        ul = new URL("https://pizzarun-17.000webhostapp.com/LogIn.php?UrN=" +
                                UN + "&PsW=" + UP);

                        HttpURLConnection conexion = (HttpURLConnection) ul.openConnection();
                        try {
                            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                                String linea = reader.readLine();
                                if (linea.equals("OK")) {
                                    Intent i = new Intent(v.getContext(), MainActivity.class);//cambiar clase a menú
                                    GuardarURL();
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Usuario o Contraseña incorrecto", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Usuario o Contraseña incorrecto", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, "Usuario o Contraseña incorrecto", Toast.LENGTH_SHORT).show();
                        } finally {
                            conexion.disconnect();
                        }
                    } catch (IOException e) {
                        Log.e("PizzaRun", e.getMessage(), e);
                    }
                }
            }
        });


        Button btnregistrer = (Button) findViewById(R.id.btn_Registrarse);
        btnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegistroActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        Button newContra = (Button) findViewById(R.id.button7);
        newContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecuperarContrasenaActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    public void GuardarURL(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        preferences.edit().putString(STRING_LINK, ul.toString()).apply();
    }

}
