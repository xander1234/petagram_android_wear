package co.com.pets.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import co.com.pets.R;
import co.com.pets.adapter.MascotaAdaptador;
import co.com.pets.db.ConstructorMascotas;
import co.com.pets.pojo.Mascota;

public class MascotasFavoritas extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);


        Toolbar miActionBar = (Toolbar) findViewById(R.id.actionBarMascotasFavoritas);
        setSupportActionBar(miActionBar);

        //activa el boton de subir
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        inicializarListaContactos();

        if(mascotas.size() > 0) {
            inicializarAdaptador();
        }
    }

    /*

    public MascotaAdaptador adaptador;

    public void  inicializarAdaptador(){
        adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }


    public void inicializarListaContactos(){

        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.cerdo,"Cerdo",5));
        mascotas.add(new Mascota(R.drawable.elefante,"Elefante",5));
        mascotas.add(new Mascota(R.drawable.leon,"Leon",5));
        mascotas.add(new Mascota(R.drawable.panda_bear,"Oso Panda",3));
        mascotas.add(new Mascota(R.drawable.vaca,"Vaca",2));
    }
    */

    public void inicializarAdaptador(){
            MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,this);
            listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaContactos(){

        mascotas = new ArrayList<>();
        ConstructorMascotas constructorMascotas = new ConstructorMascotas(this);
        mascotas = constructorMascotas.obtenerPreferidas();

    }



}
