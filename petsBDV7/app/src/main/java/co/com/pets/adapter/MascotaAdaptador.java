package co.com.pets.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.pets.db.ConstructorMascotas;
import co.com.pets.pojo.Mascota;
import co.com.pets.R;

/**
 * Created by xander on 22/01/2017.
 */


public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas,Activity activity ){
        this.mascotas = mascotas;
        this.activity = activity;
    }


    //inflar el layout y lo pasara al viewholder para que obtenga los views
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    //asocia cada elemento de la lista a cada View
    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, final int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgfoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombre());
        mascotaViewHolder.tvRanking.setText(String.valueOf(mascota.getLikes()));

        mascotaViewHolder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "I Like Pet "+ mascota.getNombre()+"!", Toast.LENGTH_SHORT).show();

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.editarLike(mascota,position+1);
                mascotaViewHolder.tvRanking.setText(constructorMascotas.obtenerLikesMascota(mascota)+ "" );

                mascota.setLikes(mascota.getLikes()+1);
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() { //cantidada de elementos de mi lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgfoto;
        private TextView tvNombreCV;
		private TextView tvRanking;
        private ImageButton btnFavorite;
        

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgfoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            btnFavorite = (ImageButton) itemView.findViewById(R.id.btnFavorite);
            tvRanking   = (TextView) itemView.findViewById(R.id.tvRanking);
        }
    }

}