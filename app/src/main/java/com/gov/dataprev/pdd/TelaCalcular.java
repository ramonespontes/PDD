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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.zip.DataFormatException;

public class TelaCalcular extends AppCompatActivity {

    int diasDDA, diasBanco, diasInsumos, diasTestDesemp, diasDDANaoUtil, diasBancoNaoUtil, diasInsumosNaoUtil, diasTestDesempNaoUtil = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_calcular);



        final EditText editTextDemanda = (EditText) findViewById(R.id.editTextDemanda);
        final EditText editTextEsforco = (EditText) findViewById(R.id.editTextEsforco);
        final EditText editTextQtdPessoas = (EditText) findViewById(R.id.editTextQtdPessoas);
        final EditText editTextProdutividade = (EditText) findViewById(R.id.editTextProdutividade);
        final EditText editTextDtInicioProjeto = (EditText) findViewById(R.id.editTextDtInicioProjeto);
        final CheckBox checkBoxDDA = (CheckBox) findViewById(R.id.checkBoxDDA);
        final CheckBox checkBoxBanco = (CheckBox) findViewById(R.id.checkBoxBanco);
        final CheckBox checkBoxTest = (CheckBox) findViewById(R.id.checkBoxTest);
        final CheckBox checkBoxInsumos = (CheckBox) findViewById(R.id.checkBoxInsumos);
        Button buttonEstimar = (Button) findViewById(R.id.botaoEstimar);


        buttonEstimar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verificar se os checkbox foram marcados
                if (checkBoxDDA.isChecked()){
                    diasDDA = 5;
                    diasDDANaoUtil = 7;
                }else {
                    diasDDA = 0;
                    diasDDANaoUtil = 0;
                }

                if(checkBoxBanco.isChecked()){
                    diasBanco = 5;
                    diasBancoNaoUtil = 7;
                }else {
                    diasBanco = 0;
                    diasBancoNaoUtil = 0;
                }

                if(checkBoxInsumos.isChecked()){
                    diasInsumos = 5;
                    diasInsumosNaoUtil = 7;
                }else {
                    diasInsumos = 0;
                    diasInsumosNaoUtil = 0;
                }

                if(checkBoxTest.isChecked()){
                    diasTestDesemp = 5;
                    diasTestDesempNaoUtil = 7;
                }else {
                    diasTestDesemp = 0;
                    diasTestDesempNaoUtil = 0;
                }



                if (Util.validateNotNull(editTextDemanda, "Preencha o campo demanda") ||
                        Util.validateNotNull(editTextEsforco, "Preencha o campo esforço")
                        || Util.validateNotNull(editTextQtdPessoas, "Preencha o campo Qtd Pessoas") ||
                        Util.validateNotNull(editTextProdutividade, "Preencha o campo Produtividade")) {

                    String demanda = editTextDemanda.getText().toString();
                    int esforco = Integer.parseInt(editTextEsforco.getText().toString());
                    int qtdPessoas = Integer.parseInt(editTextQtdPessoas.getText().toString());
                    String  dataInicioProjeto =  editTextDtInicioProjeto.getText().toString();
                    int produtividade = Integer.parseInt(editTextProdutividade.getText().toString());

                    //Calcula o esforço da equipe, número de Sprints e dias úteis.
                    Util util = new Util();
                    float resultadoEsforcoEquipe = util.estimar(esforco, qtdPessoas, produtividade);
                    float numSprint = esforco / resultadoEsforcoEquipe;
                    float diasUteis = numSprint * 15 + diasDDA + diasBanco + diasInsumos + diasTestDesemp;
                    float diasNaoUteis = (numSprint * 21) + diasDDANaoUtil + diasBancoNaoUtil + diasInsumosNaoUtil + diasBancoNaoUtil; //Projeta a data fim considerando dias úteis.


                    //Transdormando a data para local Date com formatação
                    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
                    LocalDate dataInicioProjetoFormat = LocalDate.parse(dataInicioProjeto,formatter);

                    //Chamando com local date o método para calcular a data final do projeto
                    LocalDate dataFimProjeto = util.calcularDataFimProjeto(dataInicioProjetoFormat, (int) diasNaoUteis);


                    Intent i = new Intent(TelaCalcular.this, TelaResultado.class);
                    i.putExtra("demanda", demanda);
                    i.putExtra("esforco", String.valueOf(esforco));
                    i.putExtra("pessoas", String.valueOf(qtdPessoas));
                    i.putExtra("produtividade", String.valueOf(produtividade));
                    i.putExtra("numSprint", String.valueOf(numSprint));
                    i.putExtra("diasUteis", String.valueOf(diasUteis));
                    i.putExtra("dda", String.valueOf(diasDDA));
                    i.putExtra("banco", String.valueOf(diasBanco));
                    i.putExtra("insumos", String.valueOf(diasInsumos));
                    i.putExtra("teste", String.valueOf(diasTestDesemp));
                    i.putExtra("dataInicioProjeto", String.valueOf(dataInicioProjeto));
                    i.putExtra("dataFimProjeto", String.valueOf(dataFimProjeto));
                    startActivity(i);
                }



            }
        });

    }


}
