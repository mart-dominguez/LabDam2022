package com.mdgz.dam.labdam2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;
import com.mdgz.dam.labdam2022.persistencia.room.bd.BaseDeDatos;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setearToolbar();

        //------------------------------------------------------------------------------------------------
        /*
          La bd implementada por room NO se crea cuando se ejecuta build(). Se crea (si es que no existe) recien cuando
          invocas a algun metodo de algun dao.
          Por eso, invoco un metodo random aca, para que no se den problemas.
          Por que podía pasar esto (si lo invocaba directamente cuando lo necesitaba):
          - cuando presionas buscar, crea la bd y ejecuta en un hilo secundario el onCreate de la bd
            (que carga los datos iniciales). Mientras tanto, en el hilo principal, el adapter queda como nulo y la
            pantalla de resultados no muestra nada.
         */
        BaseDeDatos bd = BaseDeDatos.getInstance(getApplicationContext());
        bd.ciudadDao().getCiudadPorId(1);
        //------------------------------------------------------------------------------------------------

    }

    protected void setearToolbar(){

        //MATERIAL TOOLBAR
        MaterialToolbar toolbar = binding.toolbarPropia;
        //setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.b_buscar_button:
                        // User chose the "Settings" item, show the app settings UI...
                        return true;

                    case R.id.misReservas:

                        return true;

                    case R.id.misFavoritos:

                        return true;

                    case R.id.configuracion:
                        NavDirections action = GlobalDirections.actionGlobalSettingsFragment();
                        Navigation.findNavController(binding.fragmentContainerView).navigate(action);
                        return true;

                    default:
                        // If we got here, the user's action was not recognized.
                        return false;

                }

            }
        });

        //Toolbar "navegable" entre fragmentos
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

    }

    @Override
    protected void onDestroy() {
        // Cerrar la conexion de la base de datos
        BaseDeDatos.getInstance(getApplicationContext()).close();
        super.onDestroy();
    }

}