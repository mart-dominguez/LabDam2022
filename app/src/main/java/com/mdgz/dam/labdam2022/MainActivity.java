package com.mdgz.dam.labdam2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.mdgz.dam.labdam2022.model.Alojamiento;

public class MainActivity extends AppCompatActivity {



    private Toolbar miToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miToolbar = findViewById(R.id.mi_toolbar);
        setSupportActionBar(miToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        switch (item.getItemId()){
            case R.id.mnuOptBuscar:
                    navController.navigate(R.id.action_global_busquedaFragment);
                return true;

            case R.id.mnuOptReservas:
                navController.navigate(R.id.action_global_verReservasFragment);
                return true;
            case R.id.mnuOptFavoritos:
                    navController.navigate(R.id.action_global_verFavoritosFragment);
                return true;
            case R.id.mnuOptConfiguracion:
                navController.navigate(R.id.action_global_settingsFragment);
                return true;
            default:
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

}