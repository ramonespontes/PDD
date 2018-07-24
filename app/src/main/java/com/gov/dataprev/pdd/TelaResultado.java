package com.gov.dataprev.pdd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_resultado);

        Intent i = getIntent();
        String demanda = i.getStringExtra("demanda");
        String numSprint = i.getStringExtra("numStrint");
        String diasUteis = i.getStringExtra("diasUteis");

        TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);

        textViewResultado.setText("Prazo para atendimento da [" + demanda + "]" + " são "  + numSprint + " Sprint(s), sendo " + diasUteis + "dias úteis!");

    }
}
