package com.mdgz.dam.labdam2022.utilities;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mdgz.dam.labdam2022.R;
import com.mdgz.dam.labdam2022.databinding.CardAlojamientoBinding;
import com.mdgz.dam.labdam2022.databinding.CardAlojamientoExpandableBinding;
import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Favorito;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.interfaces.FavoritoDataSource;
import com.mdgz.dam.labdam2022.persistencia.retrofit.impl.FavoritoRetrofitDataSource;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.FavoritoHabitacionEntity;
import com.mdgz.dam.labdam2022.persistencia.room.entidades.UsuarioEntity;
import com.mdgz.dam.labdam2022.repositorios.FavoritoRepository;
import com.mdgz.dam.labdam2022.repositorios.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class AlojamientoAdapter extends RecyclerView.Adapter<AlojamientoAdapter.AlojamientoViewHolder>
{

    private List<Alojamiento> alojamientos = new ArrayList<>();
    private Activity activity;

    private static List<Favorito> favoritos = new ArrayList<>();    //Los favoritos
    private static List<Alojamiento> favoritosAlojamiento = new ArrayList<>();  //Sus alojamientos correspondientes
    private static FavoritoRepository favoritoRepository;

    private static Usuario[] usuario = new Usuario[1];


    public AlojamientoAdapter(Activity activity)
    {
        this.activity = activity;
        favoritoRepository = FavoritoRepository.getInstance();
        UsuarioRepository.getInstance().getTodos((exito, resultados) -> {usuario[0] = resultados.get(0);});
    }

    @NonNull
    @Override       //Metodo que se ejecuta una vez por cada fila que se visualiza
    public AlojamientoAdapter.AlojamientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int tipo)
    {
        return new AlojamientoViewHolder(
                CardAlojamientoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
                activity, parent.getContext(),LayoutInflater.from(parent.getContext()));
                //Argumentos adicionales para crear el AlertDialog
    }


    @Override
    public int getItemCount(){
        return alojamientos.size();
    }

    //Por cada fila que se desea visualizar, seteamos los datos (por sobre la existente)
    @Override
    public void onBindViewHolder(AlojamientoViewHolder alojamientoHolder, final int position)
    {
        //Se obtiene el holder con la vista inflada, y se le aplica la logica para setear los nuevos datos
        alojamientoHolder.bind(alojamientos.get(position));

    }

    //View Holder, encargado de "buscar los widgets"
    public static class AlojamientoViewHolder extends RecyclerView.ViewHolder
    {
        private CardAlojamientoBinding binding;
        private CardAlojamientoExpandableBinding expandableBinding;

        //EstadiaAlertDialog
        private Activity activity;
        private Context context;
        private LayoutInflater inflater;


        //Constructor
        public AlojamientoViewHolder(CardAlojamientoBinding binding, Activity activity, Context context, LayoutInflater inflater) //Recibe la vista inflada
        {
            super(binding.getRoot());
            this.binding = binding;
            expandableBinding = binding.caExpandable;
            this.activity = activity;
            this.context = context;
            this.inflater = inflater;
        }

        //1. Seteo de los "nuevos" datos del alojamiento de la fila asociada al holder
        public void bind(Alojamiento alojamiento)
        {

            binding.caFavoriteButton.setOnClickListener(view ->
            {
                //Logica para agregar a favoritos: si hace clic con el corazon vacio -> guarda favorito
                //                                 si hace clic con el corazon lleno -> elimina favorito
                if(favoritosAlojamiento.contains(alojamiento))
                {
                    int index = favoritosAlojamiento.indexOf(alojamiento);

                    AlojamientoAdapter.favoritoRepository.eliminar(favoritos.get(index),exito ->
                    {
                        if(exito && favoritosAlojamiento.contains(alojamiento)) {
                            /*
                                Agregue la segunda condicion porque, dado que se llama a los dos metodos (room y retrofit)
                                y ambos tienen el mismo callback, cuando se invoque por segunda vez, el favorito en la lista
                                no va a existir mas
                             */
                            favoritosAlojamiento.remove(index);
                            favoritos.remove(index);
                            binding.caFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        }
                    });
                }else
                {
                    Favorito favorito = new Favorito(UUID.randomUUID(),usuario[0],alojamiento);
                    AlojamientoAdapter.favoritoRepository.guardar(favorito,exito ->
                    {
                        if(exito && !favoritosAlojamiento.contains(alojamiento)) {
                            /*
                                Agregue la segunda condicion porque, dado que se llama a los dos metodos (room y retrofit)
                                y ambos tienen el mismo callback, cuando se invoque por segunda vez, el favorito ya fue añadido
                                a la lista de favoritos
                             */
                            favoritosAlojamiento.add(alojamiento);
                            favoritos.add(favorito);
                            binding.caFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_filled_24);
                        }
                    });

                }
            });

            //Reservar
            binding.caReservarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EstadiaDialog.create(activity,inflater,context,alojamiento).show();
                }
            });

            cardBehaviour();
            cardFormat(alojamiento);
        }

        //1.1 Establecer el comportamiento del card (primero aparece "sin expandir")
        private void cardBehaviour()
        {
            binding.caExpandirButton.setText("Expandir");
            binding.caExpandable.getRoot().setVisibility(ConstraintLayout.GONE);
            binding.caExpandirButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(binding.caExpandable.getRoot().getVisibility() == ConstraintLayout.VISIBLE)
                    {
                        binding.caExpandable.getRoot().setVisibility(ConstraintLayout.GONE);
                        binding.caExpandirButton.setText("Expandir");
                    }
                    else
                    {
                        binding.caExpandable.getRoot().setVisibility(ConstraintLayout.VISIBLE);
                        binding.caExpandirButton.setText("Cerrar");
                    }
                }
            });
        }

        //1.2 Seteo de los datos del alojamiento
        private void cardFormat(Alojamiento alojamiento)
        {
            Random r = new Random();
            float rating = r.nextFloat() * 5;
            int resenias = r.nextInt(1000);
            int banio = r.nextInt(3);
            int cocina = r.nextInt(3);
            int pieza = r.nextInt(6);
            int wifi = r.nextInt(2);
            int desayuno = r.nextInt(2);
            int cochera = r.nextInt(2);
            int abierto = r.nextInt(2);


            if(alojamiento.getClass().equals(Departamento.class)) //Departamento
            {
                binding.caTipoImage.setImageResource(R.drawable.ic_outline_house_24);
                binding.caTipoCaption.setText("Departamento");

                binding.caLimpiezaText.setVisibility(TextView.VISIBLE);
                binding.caLimpiezaText.setText(((Departamento) alojamiento).getCostoLimpieza() + " limpieza");

                String valor = ((Departamento) alojamiento).getTieneWifi() ? "Con Wi-Fi" : "Sin Wi-Fi";
                expandableBinding.caeWifiValor.setText(valor);
                expandableBinding.caePiezaValor.setText("" + ((Departamento) alojamiento).getCantidadHabitaciones());
                valor = cochera == 1 ? "Sin cochera" : "Con cochera";
                expandableBinding.caeCocheraValor.setText(valor);

            } else { //Hotel
                binding.caTipoImage.setImageResource(R.drawable.ic_baseline_apartment_24);
                binding.caTipoCaption.setText("Hotel");
                binding.caLimpiezaText.setVisibility(TextView.GONE);

                String valor = "Con Wi-Fi";
                expandableBinding.caeWifiValor.setText(valor);
                valor = ((Habitacion) alojamiento).getTieneEstacionamiento() ? "Con cochera" : "Sin cochera";
                expandableBinding.caeCocheraValor.setText(valor);

            }

            binding.caTitle.setText(alojamiento.getTitulo());
            binding.caRating.setRating(rating);
            binding.caRatingCaption.setText(Utilities.round2(rating) + " (" + resenias + ")");
            binding.caPrecioText.setText("$"+ alojamiento.getPrecioBase() + "/noche");
            binding.caDescription.setText(alojamiento.getDescripcion());
            binding.caUbicacion.setText(alojamiento.getUbicacion().getCalle() + " " + alojamiento.getUbicacion().getNumero() + ", " + alojamiento.getUbicacion().getCiudad().getNombre());


            expandableBinding.caePersonasValor.setText(alojamiento.getCapacidad() + " ocupantes");
            expandableBinding.caeBanioValor.setText("" + banio);
            expandableBinding.caeCocinaValor.setText("" + cocina);
            expandableBinding.caePiezaValor.setText("" + pieza);

            String valor;
            valor = desayuno == 1 ? "Sin desayuno" : "Con desayuno";
            expandableBinding.caeDesayunoValor.setText(valor);
            valor = abierto == 1 ? "Cerrado" : "Abierto ahora";
            expandableBinding.caeSituacionTitle.setText(valor);

            //Comprobar si el alojamiento ya fue añadido a favoritos anteriormente
            if(favoritosAlojamiento.contains(alojamiento))
            {
                binding.caFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_filled_24);
            }

        }

    }


    public void setData(List<Alojamiento> lista,List<Favorito> favoritos)
    {
        AlojamientoDifference diferenciador = new AlojamientoDifference(lista,alojamientos);
        DiffUtil.DiffResult resultado = DiffUtil.calculateDiff(diferenciador);
        alojamientos.clear();
        alojamientos.addAll(lista);

        //Obtengo los favoritos y los mapeo a sus alojamientos
        AlojamientoAdapter.favoritos.clear();
        AlojamientoAdapter.favoritosAlojamiento.clear();
        List<Alojamiento> favs = favoritos.stream().map(Favorito::getAlojamiento).collect(Collectors.toList());
        AlojamientoAdapter.favoritosAlojamiento.addAll(favs);
        AlojamientoAdapter.favoritos.addAll(favoritos);

        //Avisar de cambio en valores al adaptador
        resultado.dispatchUpdatesTo(this);
    }

    /*  Esta clase permite calcular la diferencia entre 2 listas de datos distintas.
        Esto permite que, cuando se cambia la lista de datos del adapter, el recyclerView tenga que hacer menos operaciones costosas para recalcular
        lo que debe mostrar.
        Esta clase no es obligatoria, pero ayuda a reducir la cantidad de cálculos, porque sin esta información el recyclerView tiene que destruir y
        recrear la vista entera.
    * */
    public static class AlojamientoDifference extends DiffUtil.Callback
    {

        private final List<Alojamiento> nueva;
        private final List<Alojamiento> vieja;

        public AlojamientoDifference(List<Alojamiento> nueva, List<Alojamiento> vieja)
        {
            this.nueva = nueva;
            this.vieja = vieja;
        }

        @Override
        public int getOldListSize() {
            return vieja.size();
        }

        @Override
        public int getNewListSize() {
            return nueva.size();
        }

        @Override       //Son las 2 instancias iguales?
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            Alojamiento viejo = vieja.get(oldItemPosition);
            Alojamiento nuevo = nueva.get(newItemPosition);
            return nuevo.equals(viejo);
        }

        @Override       //Tienen las 2 instancias los mismos datos?
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Alojamiento viejo = vieja.get(oldItemPosition);
            Alojamiento nuevo = nueva.get(newItemPosition);
            return viejo.getId().equals(nuevo.getId()) &&
                    viejo.getTitulo().equals(nuevo.getTitulo()) &&
                    viejo.getCapacidad().equals(nuevo.getCapacidad()) &&
                    viejo.getDescripcion().equals(nuevo.getDescripcion()) &&
                    viejo.getPrecioBase().equals(nuevo.getPrecioBase());
        }
    }


}
