package com.gov.dataprev.pdd;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

        //Após a abertura da notification redireciona para essa Tela
        Intent intent = new Intent(this, TelaConfigurar.class);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Constrói o build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        //Cria a notificação com as informações da notificação
        Notification notificacao = builder.setContentTitle(titulo).setContentText(mensagem).setSmallIcon(R.drawable.dataprev).setContentIntent(pendingIntent).build();

        //Chama/cria o serviço de notificação
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //A partir do serviço, passa a notificação criada.
        notificationManager.notify(0, notificacao);


    }


}
