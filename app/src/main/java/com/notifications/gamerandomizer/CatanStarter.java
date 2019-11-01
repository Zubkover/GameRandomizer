package com.notifications.gamerandomizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CatanStarter extends AppCompatActivity {

    private Button finish;

    Spinner if7;
    String[] if7Options = new String[]{"No","Yes"};
    Spinner dice;
    String[] diceOptions = new String[]{"2D6", "1D12"};
    Spinner player;
    String[] playerOptions = new String[]{"2", "3", "4"};
    Spinner season;
    String[] seasonOptions = new String[]{"No modifier", "Random modifier for each player's turn", "Random modifier for each round", "Real-life Seasons"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catan_starter);

        finish = (Button) findViewById(R.id.buttonFinish);


        if7 = findViewById(R.id.spinnerCount7);
        ArrayAdapter<String> adapterIf7 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, if7Options);
        if7.setSelection(0);
        if7.setAdapter(adapterIf7);

        dice = findViewById(R.id.spinnerDice);
        ArrayAdapter<String> adapterDice = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, diceOptions);
        dice.setSelection(0);
        dice.setAdapter(adapterDice);

        season = findViewById(R.id.spinnerSeason);
        ArrayAdapter<String> adapterSeason= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, seasonOptions);
        season.setSelection(0);
        season.setAdapter(adapterSeason);

        player = findViewById(R.id.spinnerPlayer);
        ArrayAdapter<String> adapterPlayer= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, playerOptions);
        player.setSelection(0);
        player.setAdapter(adapterPlayer);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(new Catan());
            }
        });
    }

    public void openPage(Object object) {
        Intent intent = new Intent(this, Catan.class);
        intent.putExtra("sevenMode", if7.getSelectedItemPosition());
        intent.putExtra("diceMode", dice.getSelectedItemPosition());
        intent.putExtra("playerMode", player.getSelectedItemPosition());
        intent.putExtra("seasonMode", season.getSelectedItemPosition());
        startActivity(intent);
    }
}
