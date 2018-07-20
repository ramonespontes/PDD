package com.gov.dataprev.pdd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TelaCalcular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_calcular);

        Button button = (Button) findViewById(R.id.botaoEstimar);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(TelaCalcular.this, "Erasto é gente fina", Toast.LENGTH_LONG).show();
           }
       });

    }

//    public void estimar(){
//        Toast.makeText(TelaCalcular.this, "Erasto é gente fina", Toast.LENGTH_LONG);
//    }
}
