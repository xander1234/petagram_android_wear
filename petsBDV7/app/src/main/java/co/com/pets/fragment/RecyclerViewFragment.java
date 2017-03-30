package co.com.pets.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.com.pets.db.ConstructorMascotas;
import co.com.pets.pojo.Mascota;
import co.com.pets.adapter.MascotaAdaptador;
import co.com.pets.R;

//pertenece a la pagina principal de mascotas - vaca, cerdo, leon, etc
public class RecyclerViewFragment extends Fragment {

    private RecyclerView listaMascotas;
    ArrayList<Mascota> mascotas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recyclerview,container, false);
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        inicializarListaContactos();
        inicializarAdaptador();

        return v;
    }



    public void inicializarAdaptador(){

        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);

    }

    public void inicializarListaContactos(){

        mascotas = new ArrayList<>();
        ConstructorMascotas constructorMascotas = new ConstructorMascotas( getActivity());
        mascotas = constructorMascotas.obtenerMascotas();

    }


}
