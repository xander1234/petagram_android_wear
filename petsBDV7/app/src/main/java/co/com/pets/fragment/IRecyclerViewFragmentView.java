package co.com.pets.fragment;

import java.util.ArrayList;

import co.com.pets.adapter.MascotaAdaptador;
import co.com.pets.pojo.Mascota;
import co.com.pets.pojo.MascotaRestApi;


/**
 * Created by xander on 04/02/2017.
 */

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public MascotaAdaptador crearAdaptadorRestApi(ArrayList<MascotaRestApi> mascotas);

	public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}
