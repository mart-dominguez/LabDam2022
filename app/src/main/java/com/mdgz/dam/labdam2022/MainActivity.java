package com.mdgz.dam.labdam2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mdgz.dam.labdam2022.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(findViewById(R.id.materialToolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Fragment fragment = binding.fragmentContainerView.getFragment();
         navController = NavHostFragment.findNavController(fragment);
        switch (item.getItemId()){
            case R.id.opcion_configuracion:
                if(navController.getCurrentDestination().getId() != R.id.configuracionFragment) {
                    navController.navigate(R.id.to_configuracionFragment);
                    return true;
                }
            case R.id.opcion_buscar:
                if(navController.getCurrentDestination().getId() != R.id.opcion_buscar){
                    navController.navigate(R.id.to_busquedaFragment);
                }
            default:  return false;
        }
    }

}