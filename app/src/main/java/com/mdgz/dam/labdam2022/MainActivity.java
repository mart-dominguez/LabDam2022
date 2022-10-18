package com.mdgz.dam.labdam2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        setSupportActionBar(binding.toolbar);
//        ActionBar ab = getSupportActionBar();
////        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
////        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setTitle("Titulo toolbar");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch(item.getItemId()){
            case R.id.nav_busqueda:
                BusquedaFragment busquedaFragment = new BusquedaFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, busquedaFragment)
                        .addToBackStack(null).commit();
                break;
            case R.id.nav_reservas:
                MisReservasFragment misReservasFragment = new MisReservasFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, misReservasFragment)
                        .addToBackStack(null).commit();
                break;
            case R.id.nav_favoritos:
                MisFavoritosFragment misFavoritosFragment = new MisFavoritosFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, misFavoritosFragment)
                        .addToBackStack(null).commit();
                break;
            case R.id.nav_configuracion:
                ConfiguracionFragment configuracionFragment = new ConfiguracionFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, configuracionFragment)
                        .addToBackStack(null).commit();
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }
}