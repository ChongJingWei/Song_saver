package sg.edu.rp.c346.id22024709.songsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView teTitle,teSinger, teYear;
    RadioGroup rgStars;
    Button insert, showList;
    ArrayList<Song> songal;
    RadioButton rg1, rg2, rg3, rg4, rg5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teTitle = findViewById(R.id.teTitle);
        teSinger = findViewById(R.id.teSinger);
        teYear = findViewById(R.id.teYear);
        rgStars = findViewById(R.id.rgStars);
        insert =  findViewById(R.id.insert);
        showList = findViewById(R.id.showList);
        rg1 = findViewById(R.id.r1);
        rg2 = findViewById(R.id.r2);
        rg3 = findViewById(R.id.r3);
        rg4 = findViewById(R.id.r4);
        rg5 = findViewById(R.id.r5);
        songal = new ArrayList<Song>();


        ArrayAdapter aaSong =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,songal);

        insert.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                int starz;
                if (rg1.isChecked()){
                   starz=1;
                } else if (rg2.isChecked()){
                    starz=2;
                } else if (rg3.isChecked()){
                    starz=3;
                } else if (rg4.isChecked()){
                    starz=4;
                } else if (rg5.isChecked()){
                   starz=5;
                } else {
                    starz=1;
                }

                DBHelper db = new DBHelper(MainActivity.this);
                Song newSong = new Song (teTitle.getText().toString(), teSinger.getText().toString(), Integer.parseInt(teYear.getText().toString()),starz);
                db.insertSong(teTitle.getText().toString(), teSinger.getText().toString(), Integer.parseInt(teYear.getText().toString()),starz);
                songal.add(newSong);
                teTitle.setText("");
                teSinger.setText("");
                teYear.setText("");
            }
        });

        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                Intent display = new Intent(MainActivity.this, Showlist.class);
                display.putExtra("ARRAYLIST", songal);
                startActivity(display);
            }
        });
    }
}