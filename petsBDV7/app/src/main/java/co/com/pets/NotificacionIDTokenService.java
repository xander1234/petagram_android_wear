package co.com.pets;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by xander on 12/03/2017.
 */

public class NotificacionIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
//        super.onTokenRefresh();
        Log.d(TAG, "Solicitando token: ");
        String token = FirebaseInstanceId.getInstance().getToken();

        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token){
        Log.d(TAG, "Refreshed token: " + token);

    }

}
