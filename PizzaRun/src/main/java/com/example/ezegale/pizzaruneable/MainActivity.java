package com.example.ezegale.pizzaruneable;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements CerrarSession.OnFragmentInteractionListener,NavigationView.OnNavigationItemSelectedListener, Inicio.OnFragmentInteractionListener,Pago.OnFragmentInteractionListener, TusPedidos.OnFragmentInteractionListener,Promociones.OnFragmentInteractionListener,Ayuda.OnFragmentInteractionListener,TusFavoritos.OnFragmentInteractionListener,Configuracion.OnFragmentInteractionListener,Ingredientes.OnFragmentInteractionListener {

    private static final String STRING_PREFERENCES = "ezegale.pizzaruneable";
    private static final String STRING_LINK= "NO";
    private static final String STRING_USER= "";
    private Button mapa;

    private TextView nombre ;

    public void GuardarURL(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            preferences.edit().putString(STRING_LINK, "NO").apply();
        }
    }

    public String ObtenerUsuario(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES, MODE_PRIVATE);
        return preferences.getString(STRING_USER, "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mapa=(Button)findViewById(R.id.mapa);

        mapa.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                Intent inten2 = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(inten2);

            }
        });

        Fragment fragment = new Inicio();
        getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();

        String UN = ObtenerUsuario();
        nombre = (TextView) findViewById(R.id.nombreUsuario);
        String linea = "";
        try {
            URL ul = new URL("http://pizzarun-17.000webhostapp.com/obtenerInfoUsuario.php?UrN=" + UN);
            HttpURLConnection conexion = (HttpURLConnection) ul.openConnection();
            try {
                if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                    String lineatemp = reader.readLine();
                    //while (lineatemp != null){
                    linea += lineatemp;
                    // }

                }
            } catch (Exception e) {
            } finally {
                conexion.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //InfoUsuario ifUs = null;
        //try {
          //  if (linea!= ""){
              //  JSONArray jsonObj = new JSONArray(linea);

              //  for(int i = 0; i>jsonObj.length(); i++) {
                   // JSONObject jsonOb = jsonObj.getJSONObject(i);
                    //ifUs = new InfoUsuario();
                    //ifUs.get_nombre(jsonOb.optString("nombre"));
                   // nombre.setText(linea.toString());
          //      }
         //   }

      //  }catch (Exception e){

      //  }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
            /*Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Boolean FragmentoSeleccionado=false;
        if (id == R.id.Inicio) {
            fragment = new Inicio();
            FragmentoSeleccionado =true;
            mapa.setVisibility(mapa.VISIBLE);
        }
        else if (id == R.id.IdPago) {
            fragment = new Pago();
            FragmentoSeleccionado =true;
            mapa.setVisibility(mapa.INVISIBLE);
        } else if (id == R.id.IdTusPedidos) {
            //fragment = new TusPedidos();
            fragment = new Ingredientes();
            FragmentoSeleccionado =true;
            mapa.setVisibility(mapa.INVISIBLE);
        } else if (id == R.id.IdPromociones) {
            fragment = new Promociones();
            FragmentoSeleccionado =true;
            mapa.setVisibility(mapa.INVISIBLE);
        } else if (id == R.id.IdAyuda) {
            fragment = new Ayuda();
            FragmentoSeleccionado =true;
            mapa.setVisibility(mapa.INVISIBLE);
        }else if (id == R.id.IdTusFavoritps) {
            fragment = new TusFavoritos();
            FragmentoSeleccionado =true;
            mapa.setVisibility(mapa.INVISIBLE);

        } else if (id == R.id.IdConfiguracion) {
            fragment = new Configuracion();
            FragmentoSeleccionado =true;
            mapa.setVisibility(mapa.INVISIBLE);
        }else if (id == R.id.IdCerrarSession) {
            fragment = new CerrarSession();
            GuardarURL();
            Intent inten2 = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(inten2);
            finish();

           }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (FragmentoSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor,fragment).commit();
        }
        return true;

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }




}
