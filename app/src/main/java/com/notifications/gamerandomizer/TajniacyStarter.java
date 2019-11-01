package com.notifications.gamerandomizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TajniacyStarter extends AppCompatActivity {

    private Button finish;

    Spinner languages;
    String[] languagesOptions = new String[]{"English", "Polish"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tajniacy_starter);

        finish = (Button) findViewById(R.id.buttonFinish);


        languages = findViewById(R.id.spinnerLanguages);
        ArrayAdapter<String> adapterLanguages = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, languagesOptions);
        languages.setSelection(0);
        languages.setAdapter(adapterLanguages);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(new Tajniacy());
            }
        });
    }

    public void openPage(Object object) {
        Intent intent = new Intent(this, Tajniacy.class);
        intent.putExtra("language", languages.getSelectedItemPosition());
        startActivity(intent);
    }
}
