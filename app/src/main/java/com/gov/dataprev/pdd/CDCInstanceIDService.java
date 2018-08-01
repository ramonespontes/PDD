package com.gov.dataprev.pdd;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class CDCInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token da App", token);

        enviaTokenParaServidor(token);
    }

    private void enviaTokenParaServidor(String refreshedToken) {

    }
}
