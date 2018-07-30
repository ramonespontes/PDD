package com.gov.dataprev.pdd;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class CDCMessasingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        mostrarNotificacao(notification);
    }

    public void mostrarNotificacao(RemoteMessage.Notification notification) {

        String titulo = notification.getTitle();
        String mensagem = notification.getBody();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        Notification notificacao = builder.setContentTitle(titulo).setContentText(mensagem).setSmallIcon(R.drawable.dataprev).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificacao);
    }


}
