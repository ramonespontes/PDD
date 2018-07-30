package com.gov.dataprev.pdd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TelaResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_resultado);

        Button buttonEnviarEmail = (Button) findViewById(R.id.buttonEnviar);
        final EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        Intent i = getIntent();
        final String demanda = i.getStringExtra("demanda");
        String esforco = i.getStringExtra("esforco");
        String pessoas = i.getStringExtra("pessoas");
        String produtividade = i.getStringExtra("produtividade");
        String numSprint = i.getStringExtra("numSprint");
        String diasUteis = i.getStringExtra("diasUteis");
        String dataInicioProjeto = i.getStringExtra("dataInicioProjeto");
        String dataFimProjeto = i.getStringExtra("dataFimProjeto");

        final TextView textViewTextParametrosResultado = (TextView) findViewById(R.id.textViewTextParametrosResultado);
        final TextView textViewResultado = (TextView) findViewById(R.id.textViewResultado);

        //Formatando o número de sprints em duas casas decimais.
        DecimalFormat numSprintFomatado = new DecimalFormat("0.00");

        textViewTextParametrosResultado.setText("Demanda: " + demanda + "\n" + "Esforço: " + esforco + "\n" + "Pessoas: " + pessoas + "\n" + "Produtividade: " + produtividade);
        textViewResultado.setText("Prazo da Demanda: " + demanda + "\n" + " Sprint(s) " + numSprint.substring(0,4) + "\n" + " Dias úteis: " + diasUteis + "\n" +
                "Data de início: " + dataInicioProjeto + "\n" + "Data Fim: " + dataFimProjeto);

        buttonEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = textViewTextParametrosResultado.getText().toString();

                Intent intentEmail = new Intent(Intent.ACTION_SEND);
                intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[] {editTextEmail.getText().toString()}); // Enviar para> , "ramonespontes@gmail.com", "erastoalpes@gmail.com"
                intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Teste alfa do APP: PDD");// ASSUNTO
                //intentEmail.putExtra(Intent.EXTRA_CC, editTextEmail.getText().toString());
                intentEmail.putExtra(Intent.EXTRA_TEXT, textViewResultado.getText().toString());
                intentEmail.setType("plain/text");


                try {
                    startActivity(intentEmail);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(TelaResultado.this, "Não foi localizado software para o envio de email.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
