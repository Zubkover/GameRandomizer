package com.notifications.gamerandomizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Catan extends AppCompatActivity {

    static Random r = new Random();

    int counterTurn;
    int counterRound;
    static String val = "";
    static String effect = "";
    static String playerOut = "Player is out";
    static String extraRes = "Player gets each resource extra";

    int mainDice;
    int firstSeason;
    int counterSeason = firstSeason;
    int currentPlayer;

    int diceMode;
    int playerMode;
    int seasonMode;
    int sevenMode;

    private Button Generate;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catan);

        Generate = (Button) findViewById(R.id.buttonGenerateC);

        text = findViewById(R.id.textContent);

        firstSeason = 2;
        counterRound = 1;
        counterTurn = 1;
        counterSeason = firstSeason;
        currentPlayer = 1;

        getAllInputs();
        decipherInputs();

        Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGenerate();
            }
        });
    }

    private void decipherInputs() {
        switch (diceMode){
            case 0:
                mainDice = 6;
                break;
            case 1:
                mainDice = 12;
                break;
        }

        switch (sevenMode){
            case 0:
                break;
            case 1:
                break;
        }
    }

    private void getAllInputs() {
        Bundle intent = getIntent().getExtras();
        diceMode = intent.getInt("diceMode",0);
        playerMode = intent.getInt("playerMode",2)+2;
        seasonMode = intent.getInt("seasonMode",0);
        sevenMode = intent.getInt("sevenMode", 0);
    }

    public static int diceRoll(int i) {
        int value = r.nextInt(i) + 1;
        return value;
    }

    public static int mainDiceRoll(int sides) {
        int value = 0;

        switch (sides) {
            case 6:
                value = diceRoll(6) + diceRoll(6);
                if (value==2) {
                    effect = playerOut;}
                else if (value==12) {
                    effect = extraRes;}
                else {
                    effect ="";}
                break;
            case 12:
                value = diceRoll(12);
                if (value==1) {
                    effect = playerOut;}
                else if (value==12) {
                    effect = extraRes;}
                else {
                    effect ="";}
                break;
            default: value = 69; break;
        }

        return value;
    }

    public static String seasonDice(int counter, int mode, int numPlayers, int season) {
        int value = 69;

        switch (mode) {
            case 0:
                val = "No modifier";
                break;
            case 1:
                value = diceRoll(20);
                if (value>=17) {
                    val ="Resources x2";}
                else if (value>=8) {
                    val ="Regular resources";}
                else {
                    val ="No resources";}
                break;
            case 2:
                if ((counter-1)%numPlayers==0 || counter == 1) {
                    value = diceRoll(20);
                    if (value>=17) {
                        val ="Recources x2";}
                    else if (value>=8) {
                        val ="Regular";}
                    else {
                        val ="No resources";}
                }
                break;
            case 3:
                switch (season) {
                    case 1: val = "-1 resource card"; break;
                    case 3: val = "+1 resource card"; break;
                    default: val = "Regular resources"; break;
                }
                break;
            default: val = "69"; break;
        }

        return val;
    }

    private void doGenerate() {
        text.setText("Turn of player no. " + currentPlayer + "/" + playerMode + "\n" + "Main dice roll: " + mainDiceRoll(mainDice) + "\n" + "Season modifier: " + seasonDice(counterTurn,seasonMode,playerMode, counterSeason) + "\n" + "Extra effect: " + effect + "\n" + "\n" + "Round: " + counterRound + " / " + "Turn: "+ counterTurn);
        counterTurn++;
        currentPlayer++;
        if (currentPlayer>playerMode) {
            counterRound++;
            counterSeason++;
            if (counterSeason >4) {
                counterSeason =1;
            }
            currentPlayer=1;
        }
    }

}
