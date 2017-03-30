package co.com.pets.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.com.pets.pojo.Mascota;
import co.com.pets.R;
import co.com.pets.pojo.MascotaRestApi;
import co.com.pets.restApi.ConstantesRestApi;
import co.com.pets.restApi.Endpoints;
import co.com.pets.restApi.adapter.RestApiAdapter;
import co.com.pets.restApi.model.LikeUserResponse;

import co.com.pets.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by xander on 28/01/2017.
 */

public class MascotaPortadaAdaptador extends RecyclerView.Adapter<MascotaPortadaAdaptador.MascotaViewHolder>{

    ArrayList<MascotaRestApi> mascotas;
    Activity activity;

    public MascotaPortadaAdaptador(ArrayList<MascotaRestApi> mascotas,Activity activity ){
        this.mascotas = mascotas;
        this.activity = activity;
    }


    //inflar el layout y lo pasara al viewholder para que obtenga los views
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview_detalle,parent,false);
        return new MascotaViewHolder(v);
    }

    //asocia cada elemento de la lista a cada View
    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, final int position) {

        final MascotaRestApi mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.dog_bone_white)
                .into(mascotaViewHolder.imgfoto);

//        mascotaViewHolder.imgfoto.setImageResource(mascota.getFoto());
//        mascotaViewHolder.tvRanking.setText(String.valueOf(mascota.getLikes()));

        mascotaViewHolder.tvRanking.setText(String.valueOf(mascota.getLikes()));

        mascotaViewHolder.imgfoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(activity, "Press in the image :) ", Toast.LENGTH_SHORT).show();
            }
        });

        mascotaViewHolder.imgvHuesoDetalle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(activity, "Like Foto :) " + ConstantesRestApi.ACOUNT_INSTAGRAM, Toast.LENGTH_SHORT).show();

                mascotaViewHolder.tvRanking.setText( String.valueOf(mascota.getLikes()+1) );


                String token = FirebaseInstanceId.getInstance().getToken();
                enviarTokenRegistro1(token, ConstantesRestApi.ACOUNT_INSTAGRAM, mascota.getUrlFoto());
                enviarNotificacion(token);
            }
        });

   }


    private void enviarTokenRegistro1(String token, String id_usuario_instagram, String id_foto){
        Log.d("TOKEN -", token);
        Log.d("user_instagram -", id_usuario_instagram);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establecerConexionesRestAPIFirebase();


        Call<LikeUserResponse> likeUserResponseCall = endpoints.registrarTokenIDLikeTimeline(token, id_usuario_instagram, id_foto);

        likeUserResponseCall.enqueue(new Callback<LikeUserResponse>() {

            @Override
            public void onResponse(Call<LikeUserResponse> call, Response<LikeUserResponse> response) {

                Log.d("CODE_REST", String.valueOf(response.code())); //response OK - 200

                LikeUserResponse likeUserResponse = response.body();
                Log.d("ID_FIREBASE", likeUserResponse.getId());
                Log.d("TOKEN_FIREBASE", likeUserResponse.getToken());
                Log.d("USER_FIREBASE", likeUserResponse.getId_usuario_instagram());
                Log.d("IMG_FIREBASE", likeUserResponse.getId_foto_instagram());

            }

            @Override
            public void onFailure(Call<LikeUserResponse> call, Throwable t) {

            }
        });

    }

    public void enviarNotificacion(String token){

        String emisor = "";
        String receptor = "";
        String idRegistro = "";

        if(ConstantesRestApi.ACOUNT_INSTAGRAM == "favoritepets1"){
            emisor = "favoritepets1";
            receptor = "monkeypets1";
            idRegistro = "-Kfj0RRWSYrObz8rRkUR";
        }
        if(ConstantesRestApi.ACOUNT_INSTAGRAM == "monkeypets1"){
            emisor = "monkeypets1";
            receptor = "favoritepets1";
            idRegistro = "-Kfj0YzSD3LscHLyg25a";
        }

        UsuarioResponse usuarioResponse = new UsuarioResponse(idRegistro,token ,receptor);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establecerConexionesRestAPIFirebase();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.likeImgInstagramAcount(usuarioResponse.getId(),ConstantesRestApi.ACOUNT_INSTAGRAM);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {

                Log.d("CODE_REST", String.valueOf(response.code()));

                UsuarioResponse usuarioResponse1 = response.body();
                Log.d("ID_FIREBASE", usuarioResponse1.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse1.getToken());
                //Log.d("USER_FIREBASE", usuarioResponse1.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });


    }


    @Override
    public int getItemCount() { //cantidada de elementos de mi lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgfoto;
        private TextView tvRanking;
        private ImageView imgvHuesoDetalle;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgfoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvRanking   = (TextView) itemView.findViewById(R.id.tvRanking);
            imgvHuesoDetalle   = (ImageView) itemView.findViewById(R.id.imgvHuesoDetalle);
        }
    }

}