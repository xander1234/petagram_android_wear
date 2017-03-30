package co.com.pets.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import co.com.pets.R;
import co.com.pets.adapter.MascotaPortadaAdaptador;
import co.com.pets.pojo.MascotaRestApi;
import co.com.pets.restApi.ConstantesRestApi;
import co.com.pets.restApi.EndpointsApi;
import co.com.pets.restApi.adapter.RestApiAdapter;
import co.com.pets.restApi.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    ArrayList<MascotaRestApi> mascotas;
    private RecyclerView listaMascotas;
    private final int GRID_LAYOUT_TAMANO = 3;
    public static final int USER_PRINCIPAL = 0;
    public static final int USER_SECUNDARIO = 1;

    public static final String USER_NAME_PRINCIPAL = "favoritepets1";
    public static final String USER_NAME_SECUNDARIO = "monkeypets1";

    public static int USUARIO_ACTUAL = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);


        this.listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotasDetalle);
        this.obtenerMedioRecientes();

        return v;

    }


    public void inicializarAdaptador() {

        MascotaPortadaAdaptador adaptador = new MascotaPortadaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);

        this.inicializarGridLayout();
    }

    public void inicializarGridLayout() {

        GridLayoutManager glm = new GridLayoutManager(getActivity(), GRID_LAYOUT_TAMANO);
        this.listaMascotas.setLayoutManager(glm);
    }


    public void obtenerMedioRecientes() {

        //Conexion al servidor
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        //Call<MascotaResponse> contactoResponseCall = endpointApi.getRecentMedia();
        Call<MascotaResponse> contactoResponseCall = null; //= endpointApi.getRecentMediaWafleSandbox();


        switch (USUARIO_ACTUAL) {

            case USER_PRINCIPAL:
                contactoResponseCall = endpointApi.getRecentMedia();
                ConstantesRestApi.ACOUNT_INSTAGRAM = USER_NAME_PRINCIPAL;
                break;

            case USER_SECUNDARIO:
                contactoResponseCall = endpointApi.getRecentMediaWafleSandbox();
                ConstantesRestApi.ACOUNT_INSTAGRAM = USER_NAME_SECUNDARIO;
                break;
        }

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                mascotas = contactoResponse.getMascotas();
                inicializarAdaptador();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error en la conexion..", Toast.LENGTH_LONG).show();
                Log.e("Error en la conexion..", t.toString());
            }
        });
    }


    public void mostrarMascota(ArrayList<MascotaRestApi> mascotaRestApis) {

        for (int c = 0; c < mascotaRestApis.size(); c++) {
            Toast.makeText(getActivity(), "Array " + mascotaRestApis.get(c).getUrlFoto(), Toast.LENGTH_LONG).show();
        }

    }
}
