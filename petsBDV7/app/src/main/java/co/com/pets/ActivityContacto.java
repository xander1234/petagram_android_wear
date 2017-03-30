package co.com.pets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityContacto extends AppCompatActivity {

    private Button buttonEnviar;
    private EditText TextMail;
    private EditText TextAsunto;
    private EditText TextMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        TextMail = (EditText) findViewById(R.id.etCorreo);
        TextAsunto = (EditText) findViewById(R.id.etNombre);
        TextMensaje = (EditText) findViewById(R.id.etMensaje);

        buttonEnviar = (Button) findViewById(R.id.btnContacto);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });


        Toolbar miActionBar=(Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void sendEmail() {

        Toast.makeText(this, "Email!", Toast.LENGTH_SHORT).show();

        String email = TextMail.getText().toString().trim();
        String subject = TextAsunto.getText().toString().trim();
        String message = TextMensaje.getText().toString().trim();

        SendMail sm = new SendMail(this, email, subject, message);
        sm.execute();
    }
}
