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
    ArrayList<Song> songal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songlist);

        songList = findViewById(R.id.songList);
        btnReturn = findViewById(R.id.btnReturn);

        Intent intentrec = getIntent();
        Bundle args = intentrec.getBundleExtra("BUNDLE");
        songal = (ArrayList<Song>) args.getSerializable("ARRAYLIST");
        ArrayAdapter aaSong =  new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,songal);
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
