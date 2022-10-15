package com.mdgz.dam.labdam2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

        switch (item.getItemId()){
            case R.id.mnuOptBuscar:
                /* Agregar el intento del fragmento */
                return true;
            case R.id.mnuOptReservas:
                /* Agregar el intento del fragmento */
                return true;
            case R.id.mnuOptFavoritos:
                /* Agregar el intento del fragmento */
                return true;
            case R.id.mnuOptConfiguracion:
                /* Agregar el intento del fragmento */
                return true;
            default:
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}