package co.com.pets;



import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

import static co.com.pets.R.drawable.notificacion;

/**
 * Created by xander on 12/03/2017.
 */

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    public static final int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }


        enviarNotificacion(remoteMessage);

    }

    public void enviarNotificacion(RemoteMessage remoteMessage){

        //Intent i = new Intent(this, MainActivity.class);

        Intent i = new Intent();
        i.setAction("TOUCH_USER");

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_poke,
                        getString(R.string.texto_accion_toque), pendingIntent)
                        .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(),
                                R.drawable.bk_androidwear_notification))
                        .setGravity(Gravity.CENTER_VERTICAL)
                ;

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notificacion)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(action))
                //.addAction(R.drawable.ic_full_poke, getString(R.string.texto_accion_toque), pendingIntent)
                ;

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());





    }


}
