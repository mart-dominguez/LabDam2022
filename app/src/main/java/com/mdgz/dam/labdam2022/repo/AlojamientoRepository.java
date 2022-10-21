package com.mdgz.dam.labdam2022.repo;

import com.mdgz.dam.labdam2022.model.Alojamiento;
import com.mdgz.dam.labdam2022.model.Departamento;
import com.mdgz.dam.labdam2022.model.Habitacion;
import com.mdgz.dam.labdam2022.model.Hotel;
import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.List;

public class AlojamientoRepository {

    private static final Ubicacion ubicacion1 = new Ubicacion(-42.6,-38.3,"San Martin","1989",CiudadRepository._CIUDADES.get(0));
    private static final Ubicacion ubicacion2 = new Ubicacion(-42.25,-38.2,"Lopez y Planes","2007",CiudadRepository._CIUDADES.get(1));

    public static final List<Alojamiento> _ALOJAMIENTOS = List.of(
            new Departamento(1, "Depto 1", "luminoso y amplio", 6, 300.0,true, 1500.0, 3,ubicacion1,"https://www.zonaprop.com.ar/noticias/wp-content/uploads/2016/08/depto.jpg"),
            new Departamento(2, "Depto 2", "amplio y simple", 3, 150.0,false, 1500.0, 3,ubicacion1, "https://elcomercio.pe/resizer/02VIzTJ4A2UsfFhDU5Fp-HWFLp4=/980x0/smart/filters:format(jpeg):quality(75)/arc-anglerfish-arc2-prod-elcomercio.s3.amazonaws.com/public/5N6HAL2ZAZBIFN5M4ZPSXC7LOQ.jpg"),
            new Habitacion(3, "Habitacion 1", "Espectacular suite",4, 1200.0, 2,1,true,new Hotel(1,"Hotel 1",3,ubicacion2),
                    "https://media-cdn.tripadvisor.com/media/photo-s/16/1a/ea/54/hotel-presidente-4s.jpg"),
            new Habitacion(4, "Habitacion 2", "This modern and elegantly-furnished hotel is located in the centre of Letojanni, a small and picturesque village in Taormina bay, just 80 m from the Ionian Sea.\n" +
                    "An American buffet breakfast is served in the elegant breakfast room, while drinks and apéritifs can be enjoyed at the bar or on the sea-view terrace.\n" +
                    "All rooms at the Sylesia are well-appointed and equipped with modern comforts, such as air conditioning and a minibar.",4, 1200.0, 2,1,false,new Hotel(1,"Hotel 1",3,ubicacion2),
                    "https://t-cf.bstatic.com/xdata/images/hotel/max1024x768/303082509.jpg?k=68043d46a3a4b0ae3489149ab2588e84f5b48ce1538f0d57be837aedd91f5e67&o=&hp=1"),
            new Habitacion(5, "Habitacion 3", "Espectacular suite",4, 1200.0, 2,1,true,new Hotel(1,"Hotel 1",3,ubicacion2),
                    "https://media-cdn.tripadvisor.com/media/photo-s/16/1a/ea/54/hotel-presidente-4s.jpg"),
            new Habitacion(6, "Habitacion 4", "Espectacular suite",4, 1200.0, 2,1,true,new Hotel(1,"Hotel 1",3,ubicacion2),
                    "https://media-cdn.tripadvisor.com/media/photo-s/16/1a/ea/54/hotel-presidente-4s.jpg"),
            new Habitacion(7, "Habitacion 5", "Espectacular suite",4, 1200.0, 2,1,true,new Hotel(1,"Hotel 1",3,ubicacion2),
                    "https://media-cdn.tripadvisor.com/media/photo-s/16/1a/ea/54/hotel-presidente-4s.jpg")
    );

    public List<Alojamiento> listaCiudades(){
        return  _ALOJAMIENTOS;
    }
}
