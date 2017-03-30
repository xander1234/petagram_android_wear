package co.com.pets.receivesTouch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * Created by xander on 29/03/2017.
 */

public class ShowUserMedia extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String ACTION_KEY_USER="USER1";
        String ACCION_KEY_USER_MEDIA="MEDIA_USER";

        String accion = intent.getAction();

        if(ACTION_KEY_USER.equals(accion)){
            OpenWindow(context);
        }
        if(ACCION_KEY_USER_MEDIA.equals(accion)){
            OpenWindow(context);
        }
    }

    public void OpenWindow(Context context) {
        Intent i = new Intent();

        i.setClassName("co.com.pets", "co.com.pets.MainActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

}
