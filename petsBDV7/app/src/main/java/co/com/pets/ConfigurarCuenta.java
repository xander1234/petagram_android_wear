package co.com.pets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.com.pets.fragment.PerfilFragment;

public class ConfigurarCuenta extends AppCompatActivity {


    EditText etCuentaInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        etCuentaInstagram = (EditText)findViewById(R.id.etCuentaInstagram);

        Toolbar miActionBar=(Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void btnClicGuardarPreferencia(View v){

        if(!etCuentaInstagram.getText().toString().equals("")){

            if(etCuentaInstagram.getText().toString().trim()
                    .equals(PerfilFragment.USER_NAME_PRINCIPAL)){

                PerfilFragment.USUARIO_ACTUAL = PerfilFragment.USER_PRINCIPAL;
                enviarInicio(v);

            }else if(etCuentaInstagram.getText().toString().trim()
                    .equals(PerfilFragment.USER_NAME_SECUNDARIO)){

                PerfilFragment.USUARIO_ACTUAL = PerfilFragment.USER_SECUNDARIO;
                enviarInicio(v);

            }else{
                Toast.makeText(this, "El usuario ingresado no tiene permisos en Sandbox!!", Toast.LENGTH_LONG).show();
            }

        }

    }


    private void enviarInicio(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
