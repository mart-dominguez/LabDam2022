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
        List<Alojamiento> aloj= new AlojamientoRepository().listaCiudades();
        mAdapter = new AlojamientoRecyclerAdapter(getContext(), aloj);
        recyclerView.setAdapter(mAdapter);

        if(getArguments() != null && !getArguments().isEmpty()){
            System.out.println("Se va a persisitr la busqueda");
            BusquedaDTO busq = (BusquedaDTO) getArguments().getSerializable("busqueda");
            busq.setTiempoBusqueda(System.currentTimeMillis() - busq.getTimestampInicio());
            busq.setCantidadResultados(aloj.size());
           agregarBusqueda(busq);
        }


        return thisView;
    }




    public static class AlojamientoRecyclerAdapter extends RecyclerView.Adapter<AlojamientoRecyclerAdapter.AlojamientoViewHolder> {
        private List<Alojamiento> mDataset;
        private Context mContext;

        public static class AlojamientoViewHolder extends RecyclerView.ViewHolder {
            //Aca van los atributos que queremos mostrar del alojamiento
            CardView card;
            TextView titulo;
            TextView capacidad;
            TextView precioBase;
            ImageView imgAloj;
            Button btnDetalle;
           FilaAlojamientoRecyclerBinding binding;

            public AlojamientoViewHolder(FilaAlojamientoRecyclerBinding binding) {
                    super(binding.getRoot());
                    this.binding = binding;
                //obtiene los elementos del xml de la tarjetita
                card = binding.cardAlojamiento;
                titulo = binding.txtTitulo;
                capacidad = binding.txtCapacidad;
                precioBase = binding.txtPrecioBase;
                btnDetalle = binding.btnVerDetalle;
            }
        }
        public AlojamientoRecyclerAdapter(Context context, List<Alojamiento> myDataset) {
            mDataset = myDataset;
            mContext = context;
        }
        @Override
        public AlojamientoRecyclerAdapter.AlojamientoViewHolder
        onCreateViewHolder(ViewGroup prn, int tipo) {
            // En lugar de inflar la vista manualmente, usamos la clase generada por ViewBinding para inflarla
            FilaAlojamientoRecyclerBinding binding = FilaAlojamientoRecyclerBinding.inflate(
                    LayoutInflater.from(prn.getContext()), prn, false);
            return new AlojamientoViewHolder(binding);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, final int position) {
            //Setea todos los atributos al holder de alojamiento
            Alojamiento alojamiento = mDataset.get(position);
            // alojamientoHolder.imgAloj.setTag(position);
            alojamientoHolder.btnDetalle.setTag(position);
            alojamientoHolder.titulo.setText(alojamiento.getTitulo());
            alojamientoHolder.capacidad.setText(alojamiento.getCapacidad().toString());
            alojamientoHolder.precioBase.setText(alojamiento.getPrecioBase().toString());
            //alojamientoHolder.imgAloj.setImageResource();//Buscar la imagen

            alojamientoHolder.btnDetalle.setOnClickListener(e->{
                Bundle bundle = new Bundle();
                bundle.putParcelable("alojamiento", (Parcelable) alojamiento);
                FragmentActivity activity = (FragmentActivity) mContext;
                NavController navController = NavHostFragment.findNavController(activity.getSupportFragmentManager().getPrimaryNavigationFragment());
                navController.navigate(R.id.action_global_detalleAlojamientoFragment, bundle);
            });


        }
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    public void agregarBusqueda(BusquedaDTO busq) {
        File file = new File(getContext().getFilesDir(), "busquedas.json");
        boolean existe = file.exists();

        if (!existe) {
            List<BusquedaDTO> busquedas = new ArrayList<BusquedaDTO>();
            busquedas.add(busq);
            guardarBusquedas(busquedas);
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<BusquedaDTO> busquedas;
        try {
            busquedas = objectMapper.readValue(file, new TypeReference<List<BusquedaDTO>>() {});
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