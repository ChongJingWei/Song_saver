package sg.edu.rp.c346.id22024709.songsaver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Showlist extends AppCompatActivity {

    ListView songList;
    Button btnReturn;
    ArrayList<String> songListAl;
    ArrayList<Song> songal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songlist);

        songList = findViewById(R.id.songList);
        btnReturn = findViewById(R.id.btnReturn);

        Intent intentrec = getIntent();
        songal = (ArrayList<Song>) intentrec.getSerializableExtra("ARRAYLIST");
        songListAl = new ArrayList<>(); // Initialize songListAl here
        for (int i = 0; i < songal.size(); i++) {
            songListAl.add("Title: " + songal.get(i).getTitle() + "\nSingers: " + songal.get(i).getSingers() + "\nYear: " + songal.get(i).getYear() + "\nStars: " + songal.get(i).getStar());
        }
        ArrayAdapter<String> aaSong = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songListAl);
        songList.setAdapter(aaSong);
        aaSong.notifyDataSetChanged();



        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Showlist.this, MainActivity.class);
                startActivity(back);
            }
        });
    }


}
