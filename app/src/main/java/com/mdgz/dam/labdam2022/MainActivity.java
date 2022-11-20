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
import com.mdgz.dam.labdam2022.repositorios.AlojamientoRepository;
import com.mdgz.dam.labdam2022.repositorios.CiudadRepository;
import com.mdgz.dam.labdam2022.repositorios.FavoritoRepository;
import com.mdgz.dam.labdam2022.repositorios.HotelRepository;
import com.mdgz.dam.labdam2022.repositorios.ReservaRepository;
import com.mdgz.dam.labdam2022.repositorios.UbicacionRepository;
import com.mdgz.dam.labdam2022.repositorios.UsuarioRepository;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setearToolbar();

        //Creamos todas las instancias para ya tenerlas disponibles más tarde sin necesidad del context
        AlojamientoRepository.getInstance(getApplicationContext());
        CiudadRepository.getInstance(getApplicationContext());
        FavoritoRepository.getInstance(getApplicationContext());
        HotelRepository.getInstance(getApplicationContext());
        ReservaRepository.getInstance(getApplicationContext());
        UbicacionRepository.getInstance(getApplicationContext());
        UsuarioRepository.getInstance(getApplicationContext());


    }

    protected void setearToolbar(){

        //MATERIAL TOOLBAR
        MaterialToolbar toolbar = binding.toolbarPropia;

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.b_buscar_button:
                        // User chose the "Settings" item, show the app settings UI...
                        return true;

                    case R.id.misReservas:
                        NavDirections action1 = BusquedaFragmentDirections.actionGlobalReservasFragment();
                        Navigation.findNavController(binding.fragmentContainerView).navigate(action1);

                        return true;

                    case R.id.misFavoritos:

                        return true;

                    case R.id.configuracion:
                        NavDirections action2 = GlobalDirections.actionGlobalSettingsFragment();
                        Navigation.findNavController(binding.fragmentContainerView).navigate(action2);
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