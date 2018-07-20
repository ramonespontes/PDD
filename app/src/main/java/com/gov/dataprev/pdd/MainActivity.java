package com.gov.dataprev.pdd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botaoConfigurar = (Button) findViewById(R.id.botaoConfigurar);
        Button botaoCalcular = (Button) findViewById(R.id.botaoCalcular);

        botaoConfigurar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaConfigurar.class);
                startActivity(intent);
            }
        });

        botaoCalcular.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaCalcular.class);
                startActivity(intent);
            }
        });

    }
}
