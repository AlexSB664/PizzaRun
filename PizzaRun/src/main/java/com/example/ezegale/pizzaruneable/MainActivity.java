package com.example.ezegale.pizzaruneable;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
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

public class MainActivity extends AppCompatActivity
        implements CerrarSession.OnFragmentInteractionListener,NavigationView.OnNavigationItemSelectedListener, Inicio.OnFragmentInteractionListener,Pago.OnFragmentInteractionListener, TusPedidos.OnFragmentInteractionListener,Promociones.OnFragmentInteractionListener,Ayuda.OnFragmentInteractionListener,TusFavoritos.OnFragmentInteractionListener,Configuracion.OnFragmentInteractionListener {

    private static final String STRING_PREFERENCES = "ezegale.pizzaruneable";
    private static final String STRING_LINK= "NO";
    private Button mapa;

    public void GuardarURL(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);

        preferences.edit().putString(STRING_LINK, "NO").apply();
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

        /*ImageButton btnaceituna = (ImageButton) findViewById(R.id.BtnAceituna);
        ImageButton btncebolla = (ImageButton) findViewById(R.id.BtnCebolla);
        ImageButton btnchampinon = (ImageButton) findViewById(R.id.BtnChampiñon);
        ImageButton btnchile = (ImageButton) findViewById(R.id.BtnChile);
        ImageButton btnjamon = (ImageButton) findViewById(R.id.BtnJamon);
        ImageButton btnpina = (ImageButton) findViewById(R.id.BtnPina);
        ImageButton btnpeperoni = (ImageButton) findViewById(R.id.BtnPeperoni);
        ImageButton btnqueso = (ImageButton) findViewById(R.id.BtnQueso);
        ImageButton btnsalchicha = (ImageButton) findViewById(R.id.BtnSalchicha);
        ImageButton btntocino = (ImageButton) findViewById(R.id.BtnTocino);
        final ImageView aceituna = (ImageView) findViewById(R.id.imageView);
        final ImageView cebolla = (ImageView) findViewById(R.id.imageView2);
        final ImageView champinon = (ImageView) findViewById(R.id.imageView5);
        final ImageView chile = (ImageView) findViewById(R.id.imageView7);
        final ImageView jamon = (ImageView) findViewById(R.id.imageView6);
        final ImageView pina = (ImageView) findViewById(R.id.imageView8);
        final ImageView peperoni = (ImageView) findViewById(R.id.imageView4);
        final ImageView queso = (ImageView) findViewById(R.id.imageView9);
        final ImageView salchicha = (ImageView) findViewById(R.id.imageView10);
        final ImageView tocino = (ImageView) findViewById(R.id.imageView11);
        btnaceituna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aceituna.setVisibility(View.VISIBLE);
            }
        });
        btncebolla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cebolla.setVisibility(View.VISIBLE);
            }
        });
        btnchampinon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                champinon.setVisibility(View.VISIBLE);
            }
        });
        btnchile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chile.setVisibility(View.VISIBLE);
            }
        });
        btnaceituna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aceituna.setVisibility(View.VISIBLE);
            }
        });
        btnjamon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jamon.setVisibility(View.VISIBLE);
            }
        });
        btnpina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pina.setVisibility(View.VISIBLE);
            }
        });
        btnpeperoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peperoni.setVisibility(View.VISIBLE);
            }
        });
        btnqueso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queso.setVisibility(View.VISIBLE);
            }
        });
        btnsalchicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salchicha.setVisibility(View.VISIBLE);
            }
        });
        btntocino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tocino.setVisibility(View.VISIBLE);
            }
        });*/
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
            fragment = new TusPedidos();
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
