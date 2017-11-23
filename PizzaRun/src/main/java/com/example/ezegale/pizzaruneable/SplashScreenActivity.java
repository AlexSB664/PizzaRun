package com.example.ezegale.pizzaruneable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashScreenActivity extends Activity {
    private static final String STRING_PREFERENCES = "ezegale.pizzaruneable";
    private static final String STRING_LINK = "URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String link = ObtenerURL();
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
                if (link != "NO") {

                    try {
                        URL ul = new URL("" + link);
                        HttpURLConnection conexion = (HttpURLConnection) ul.openConnection();
                        try {
                            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                                String linea = reader.readLine();
                                if (linea.equals("")) {
                                    iniciarLogin();
                                } else {
                                    iniciarMenu();
                                }
                            } else {
                                Toast.makeText(SplashScreenActivity.this, "Compruebe su conexión", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        } catch (Exception e) {
                            Toast.makeText(SplashScreenActivity.this, "Compruebe su conexión", Toast.LENGTH_LONG).show();
                        finish();
                        } finally {
                            conexion.disconnect();
                        }
                    } catch (MalformedURLException e) {
                        iniciarLogin();

                    } catch (IOException e) {
                        iniciarLogin();
                    }

                } else {
                    iniciarLogin();
                }
            }
        }, 2500);
    }

    public String ObtenerURL() {
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        return preferences.getString(STRING_LINK, "NO");
    }

    public void iniciarLogin(){
        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public  void iniciarMenu(){
        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);//cambiar clase a menú
        startActivity(i);
        finish();
    }
}
