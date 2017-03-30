package co.com.pets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import co.com.pets.restApi.Endpoints;
import co.com.pets.restApi.adapter.RestApiAdapter;
import co.com.pets.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRecibirNotificacion extends AppCompatActivity {

    EditText etCuentaFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibir_notificacion);

        Toolbar miActionBar=(Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etCuentaFirebase = (EditText)findViewById(R.id.etCuentaFirebase);
    }


    public void btnClicRegistrarDeviceFirebase(View view){

        if(!etCuentaFirebase.getText().toString().equals("")){
            String token = FirebaseInstanceId.getInstance().getToken();
            enviarTokenRegistro(token, etCuentaFirebase.getText().toString());
        }else{
            Toast.makeText(this, "Ingrese cuenta de Instagram", Toast.LENGTH_LONG).show();
        }

    }


    private void enviarTokenRegistro(String token, String id_usuario_instagram){
        Log.d("TOKEN -", token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establecerConexionesRestAPIFirebase();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenID(token, id_usuario_instagram);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {

            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {

                Log.d("CODE_REST", String.valueOf(response.code())); //response OK - 200

                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getToken());
                Log.d("USER_FIREBASE", usuarioResponse.getId_usuario_instagram());

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });

    }


}
