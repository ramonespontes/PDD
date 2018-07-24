package com.gov.dataprev.pdd;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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
}
