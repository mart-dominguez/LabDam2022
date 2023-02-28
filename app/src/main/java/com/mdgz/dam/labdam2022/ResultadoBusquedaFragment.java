package com.mdgz.dam.labdam2022;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdgz.dam.labdam2022.database.AlojamientoDataSource;
import com.mdgz.dam.labdam2022.database.room.AlojamientoRoomDataSource;
import com.mdgz.dam.labdam2022.databinding.FilaAlojamientoRecyclerBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.BusquedaDTO;
import com.mdgz.dam.labdam2022.model.Ciudad;
import com.mdgz.dam.labdam2022.repo.AlojamientoRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ResultadoBusquedaFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }
    public interface OnVerDetallesListener{
        public void verDetalles(Alojamiento alojamiento);
    }
    private static OnVerDetallesListener listenerDetalles;
    public static ResultadoBusquedaFragment newInstance(String param1, String param2) {
        ResultadoBusquedaFragment fragment = new ResultadoBusquedaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_resultado_busqueda, container, false);
        recyclerView = thisView.findViewById(R.id.recyclerAlojamiento);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        AlojamientoRoomDataSource alojamientoRoomDataSource = new AlojamientoRoomDataSource(getContext());
        AlojamientoDataSource.RecuperarAlojamientoCallback callback = (exito, resultados) -> {
            mAdapter = new AlojamientoRecyclerAdapter(getContext(), resultados, true);
            recyclerView.setAdapter(mAdapter);

            if(getArguments() != null && !getArguments().isEmpty()){
                System.out.println("Se va a persisitr la busqueda");
                BusquedaDTO busq = (BusquedaDTO) getArguments().getSerializable("busqueda");
                busq.setTiempoBusqueda(System.currentTimeMillis() - busq.getTimestampInicio());
                busq.setCantidadResultados(resultados.size());
                agregarBusqueda(busq);
            }
        };
        alojamientoRoomDataSource.recuperarAlojamiento(callback);




        return thisView;
    }



    public void agregarBusqueda(BusquedaDTO busq) {
        File file = new File(getContext().getFilesDir(), "busquedas.json");
        boolean existe = file.exists();

        if (!existe) {
            List<BusquedaDTO> busquedas = new ArrayList<>();
            busquedas.add(busq);
            guardarBusquedas(busquedas);
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<BusquedaDTO> busquedas;
        try {
            busquedas = objectMapper.readValue(file, new TypeReference<>() {
            });
            busquedas.add(busq);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        guardarBusquedas(busquedas);
    }

    private void guardarBusquedas(List<BusquedaDTO> busquedas) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(getContext().getFilesDir(), "busquedas.json");
        try {
            objectMapper.writeValue(file,busquedas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}