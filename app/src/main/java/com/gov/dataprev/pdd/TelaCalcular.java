package com.gov.dataprev.pdd;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.zip.DataFormatException;

public class TelaCalcular extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_calcular);


        final EditText editTextDemanda = (EditText) findViewById(R.id.editTextDemanda);
        final EditText editTextEsforco = (EditText) findViewById(R.id.editTextEsforco);
        final EditText editTextQtdPessoas = (EditText) findViewById(R.id.editTextQtdPessoas);
        final EditText editTextProdutividade = (EditText) findViewById(R.id.editTextProdutividade);
        final EditText editTextDtInicioProjeto = (EditText) findViewById(R.id.editTextDtInicioProjeto);
        Button buttonEstimar = (Button) findViewById(R.id.botaoEstimar);


        buttonEstimar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Util.validateNotNull(editTextDemanda,"Preencha o campo demanda")||
                        Util.validateNotNull(editTextEsforco,"Preencha o campo esforço")
                        || Util.validateNotNull(editTextQtdPessoas,"Preencha o campo Qtd Pessoas") ||
                        Util.validateNotNull(editTextProdutividade,"Preencha o campo Produtividade") ){
                    String demanda = editTextDemanda.getText().toString();

                    int esforco = Integer.parseInt(editTextEsforco.getText().toString());
                    int qtdPessoas = Integer.parseInt(editTextQtdPessoas.getText().toString());
                    // Long calendarViewDataInicioProjeto = (Long) calendarViewDataInicioProjeto.getDate();
                    int Produtividade = Integer.parseInt(editTextProdutividade.getText().toString());

                    Util util = new Util();
                    float resultadoEsforcoEquipe = util.estimar(esforco, qtdPessoas, Produtividade);
                    float numSprint = esforco / resultadoEsforcoEquipe;
                    float diasUteis = numSprint*15;


                    Intent i = new Intent(TelaCalcular.this, TelaResultado.class);
                    i.putExtra("demanda", demanda);
                    i.putExtra("numSprint", String.valueOf(numSprint));
                    i.putExtra("diasUteis", String.valueOf(diasUteis));
                    startActivity(i);
                }


                //textViewResultadoSprint.setText("Prazo para atendimento da [" + demanda + "]" + " são "  + numSprint + "Sprint(s), sendo " + numSprint*15 + "dias úteis!");


                //Toast.makeText(TelaCalcular.this, "Número de Sprint:" + numSprint, Toast.LENGTH_LONG).show();


            }
        });

    }


}
