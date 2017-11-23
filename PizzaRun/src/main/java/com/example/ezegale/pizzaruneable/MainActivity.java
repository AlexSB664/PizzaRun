package com.example.ezegale.pizzaruneable;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Inicio.OnFragmentInteractionListener,Pago.OnFragmentInteractionListener, TusPedidos.OnFragmentInteractionListener,Promociones.OnFragmentInteractionListener,Ayuda.OnFragmentInteractionListener,TusFavoritos.OnFragmentInteractionListener,Configuracion.OnFragmentInteractionListener {

    private Button mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener mlocListener = new MyLocationListener();
        mlocListener.setMainActivity(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) mlocListener);

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
            fragment = new Pago();
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
        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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
