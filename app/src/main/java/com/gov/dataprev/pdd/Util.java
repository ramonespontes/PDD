package com.gov.dataprev.pdd;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

public class Util {

    //Calcula o tempo de esforço do time, ou seja depende da quantidade de pessoas e produtividade. [A força de trabalho do time em 1 sprint]
    public float estimar(int esforco, int qdtPessoas, int produtividade){
        float result=0.0F;
        int diasSemana=5;
        int numSemandasPorSprint=3;
          result = (qdtPessoas*produtividade*diasSemana*numSemandasPorSprint);
        return result;
    }

    //Valida os campos obrigatórios do formulário
    public static boolean validateNotNull(View pView, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            String text = edText.getText().toString();
            if (text != null) {
                String strText = text.toString();
                if (!TextUtils.isEmpty(strText)) {
                    return true;
                }
            }
            // em qualquer outra condição é gerado um erro
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    //Calcula a data fim do projeto a partir da quantidade de dias úteis.
    public LocalDate calcularDataFimProjeto (LocalDate dataInicioProjeto, int dias) {

        //Formata a data fim para trabalhar com o padrão LocalDate
        String dataFimProjetoComFinalDeSemana = retornaDataFimComFinaisDeSemana(dias);
        org.joda.time.format.DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        LocalDate dataFimProjetoComFinalDeSemanaFormatado = LocalDate.parse(dataFimProjetoComFinalDeSemana,formatter);

        LocalDate weekday = dataInicioProjeto;

        if (dataInicioProjeto.getDayOfWeek() == DateTimeConstants.SATURDAY ||
                dataInicioProjeto.getDayOfWeek() == DateTimeConstants.SUNDAY) {
            weekday = weekday.plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY);
        }

        //Pega a data inicial e faz um loop adicionando dias enquanto a data for menor que a desejada.
        while (weekday.isBefore(dataFimProjetoComFinalDeSemanaFormatado)) {
            System.out.println(weekday);

            if (weekday.getDayOfWeek() == DateTimeConstants.FRIDAY)
                weekday = weekday.plusDays(3);
            else
                weekday = weekday.plusDays(1);
        }

        //Realiza a formatação da data final do projeto
        //org.joda.time.format.DateTimeFormatter formatador = DateTimeFormat.forPattern("dd/MM/yyyy");


       // weekday.toString("dd/MM/yyyy");

        return weekday;



    }

    //Calcula a data fim do projeto a partir da quantidade de dias úteis.
    public String retornaDataFimComFinaisDeSemana (int dias){

        GregorianCalendar dataFimProjeto = new GregorianCalendar();
        dataFimProjeto.add(Calendar.DAY_OF_MONTH, dias);



        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date data = dataFimProjeto.getTime();
        formato.applyPattern("dd/MM/yyyy");
        String dataFormatadaFimProjeto = formato.format(data);

        return dataFormatadaFimProjeto;
    }
}
