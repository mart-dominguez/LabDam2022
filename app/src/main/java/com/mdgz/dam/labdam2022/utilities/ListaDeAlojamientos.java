package com.mdgz.dam.labdam2022.utilities;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class ListaDeAlojamientos {

    List<Alojamiento> lista;

    public ListaDeAlojamientos(){
        lista = new ArrayList<>();

        Ubicacion ubicacionGral = new Ubicacion();

        Departamento d1, d2, d3;
        d1 = new Departamento(1, "Frente al mar", "Departamento a 10 m. del mar",
                4, 5400.0, true, 1000.0, 3,ubicacionGral);

        d2 = new Departamento(2, "En el centro", "Departamento a 5 cuadras del centro",
                5, 6500.0, true, 1000.0, 4,ubicacionGral);

        d3 = new Departamento(3, "Gran vista de la ciudad", "Departamento con una hermosa vista a la ciudad",
                2, 3050.0, true, 1000.0, 1,ubicacionGral);

        Hotel hotelGral = new Hotel();

        Habitacion h1, h2, h3;
        h1 = new Habitacion(1, "Hotel de campo", "Habitacion 5D, con vista a toda la ciudad",
                3,4200.0,1,2,true,hotelGral);

        h2 = new Habitacion(2, "Hotel bahia principe", "Habitacion 20H, con con jacuzzi!",
                5,8700.0,3,1,true,hotelGral);

        h3 = new Habitacion(3, "Hotel sol bariloche", "Habitacion con vista al Nahuel Huapi",
                3,5100.0,1,2,true,hotelGral);

        lista.add(h1);
        lista.add(d1);
        lista.add(h2);
        lista.add(d2);
        lista.add(h3);
        lista.add(d3);

    }

    public List<Alojamiento> getLista() {
        return lista;
    }
}
