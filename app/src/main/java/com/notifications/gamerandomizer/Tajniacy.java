package com.notifications.gamerandomizer;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Tajniacy extends AppCompatActivity {

    private Button Generate;
    private TextView Text;
    TableLayout Table;
    TableRow TableRow;

    String file;
    int language;

    Random generator = new Random();

    ArrayList<String> wordsList = new ArrayList<>();
    String[] words = new String[25];
    ArrayList<Integer> usedIndexes = new ArrayList<>();
    ArrayList<String> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tajniacy);

        Generate = (Button) findViewById(R.id.buttonGenerateT);
        Table = (TableLayout) findViewById(R.id.tableContent);

        getInputs();
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGenerate();
            }
        });
    }

    private void doGenerate() {
        prepare24Colors();
        randomize();
        inputLines();
    }

    private void getInputs() {
        Bundle intent = getIntent().getExtras();
        language = intent.getInt("language",0);

        switch (language) {
            case 0:
                file = "nounlist_ENG.txt";
                break;
            case 1:
                file = "nounlist_PL.txt";
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void readFile() throws IOException {
        InputStream is = getApplicationContext().getAssets().open(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = br.readLine();
        while(line != null) {
            wordsList.add(line);
            line = br.readLine();
        }

         br.close();
         is.close();
    }

    private void randomize() {
        int fileSize = wordsList.size();
        int i;

        for (int j = 0; j<25 ; j++) {
            i = generator.nextInt(fileSize);
            while (checkIndexes(i)){
                i = generator.nextInt(fileSize);
            }
            words[j] = wordsList.get(i);
            usedIndexes.add(i);
        }
    }

    private boolean checkIndexes(int currentIndex){
        int indexSize = usedIndexes.size() - 1;
        int storedIndex;
        boolean ifSame = false;

        for (int x = 0; x<=indexSize; x++) {
            storedIndex = usedIndexes.get(x);
            if(storedIndex==currentIndex) {
                ifSame = true;
                break;
            }
        }
        return ifSame;
    }

    private void inputLines() {
        int r = generator.nextInt(100);
        if (r%2==0) {
            colors.add("blue");
            Generate.setBackgroundColor(Color.rgb(89,138,198));
        }
        else {
            colors.add("red");
            Generate.setBackgroundColor(Color.rgb(160,52,6));
        }

        for(int a=0; a<=4; a++) {
            TableRow = (TableRow) Table.getChildAt(a);
            for(int b=0; b<=4; b++){
                Text = (TextView) TableRow.getChildAt(b);
                Text.setText(words[5*a+b]);
                Text.setTextColor(Color.WHITE);

                int c = generator.nextInt(colors.size());
                String color = colors.get(c);
                switch (color) {
                    case "blue":
                        Text.setBackgroundColor(Color.rgb(89,138,198));
                        break;
                    case "red":
                        Text.setBackgroundColor(Color.rgb(160,52,6));
                        break;
                    case "none":
                        Text.setBackgroundColor(Color.rgb(186,168,139));
                        break;
                    case "black":
                        Text.setBackgroundColor(Color.BLACK);
                        break;
                    default:
                        break;
                }
                colors.remove(c);
            }
        }
    }

    private void prepare24Colors() {
        for (int i =1 ; i<=7 ; i++ ) {
            colors.add("red");
            colors.add("blue");
            colors.add("none");
        }
        colors.add("red");
        colors.add("blue");
        colors.add("black");
    }
}