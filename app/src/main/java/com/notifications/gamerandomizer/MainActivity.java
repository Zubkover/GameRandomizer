package com.notifications.gamerandomizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Button buttonC;
    private Button buttonT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonC = (Button) findViewById(R.id.buttonCatan);
        buttonT = (Button) findViewById(R.id.buttonTajn);

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(new CatanStarter());
            }
        });

        buttonT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(new TajniacyStarter());
            }
        });
    }

    public void openPage(Object object) {
        Intent intent = new Intent(this, object.getClass());
        startActivity(intent);
    }
}
