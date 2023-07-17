package sg.edu.rp.c346.id22024709.songsaver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Showlist extends AppCompatActivity {

    private static final int EDIT_REQUEST_CODE = 1;
    ListView songList;
    Button btnReturn;
    ArrayList<String> songListAl;
    ArrayList<Song> songal;
    ArrayAdapter<Song> aaSong;
    ToggleButton toggleFive;
    CustomAdapter customAdapter;
    Spinner yearSpin;
    ArrayList<Integer> yearList;
    ArrayAdapter<Integer> yearAa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songlist);

        songList = findViewById(R.id.songList);
        btnReturn = findViewById(R.id.btnReturn);
        toggleFive = findViewById(R.id.toggleFive);
        yearSpin = findViewById(R.id.yearSpin);


        Intent intentrec = getIntent();
        DBHelper db = new DBHelper(Showlist.this);

        songal = db.getSong();
//        songListAl = new ArrayList<>(); // Initialize songListAl here
//        for (int i = 0; i < songal.size(); i++) {
//            songListAl.add("Title: " + songal.get(i).getTitle() + "\nSingers: " + songal.get(i).getSingers() + "\nYear: " + songal.get(i).getYear() + "\nStars: " + songal.get(i).getStar());
//        }
//        aaSong = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songal);
        customAdapter = new CustomAdapter(this, R.layout.row, songal);
        songList.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
        yearList = db.getDistYears();
        yearAa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,yearList);
        yearSpin.setAdapter(yearAa);

        toggleFive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DBHelper db = new DBHelper(Showlist.this);
                if (isChecked){
                    customAdapter.clear();
                    customAdapter.addAll(db.getSong(5));
                } else {
                    customAdapter.clear();
                    customAdapter.addAll(db.getSong());
                }
                customAdapter.notifyDataSetChanged();
            }
        });
        yearSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper db = new DBHelper(Showlist.this);


//                    case 0:
//                        customAdapter.clear();
//                        customAdapter.addAll(db.getSong());
//                        break;
//                    case 1:
//                        customAdapter.clear();
//                        customAdapter.addAll(db.getSongYear(1998));
//                        break;
//                    case 2:
//                        customAdapter.clear();
//                        customAdapter.addAll(db.getSongYear(2002));
//                        break;
//                    case 3:
//                        customAdapter.clear();
//                        customAdapter.addAll(db.getSongYear(2015));
//                        break;

                    if (position >= 0 && position < yearList.size()) {
                        int year = yearList.get(position);
                        customAdapter.clear();
                        customAdapter.addAll(db.getSongYear(year));
                    }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DBHelper db = new DBHelper(Showlist.this);
                Intent passSong = new Intent(Showlist.this,EditActivity.class);
                passSong.putExtra("position",position);
                passSong.putExtra("alPass",songal.get(position));
                startActivityForResult(passSong, EDIT_REQUEST_CODE);
            }
        });
    }

    @Override protected void onResume() {
        super.onResume();

        DBHelper db = new DBHelper(Showlist.this);
        ArrayList<Song> updatedSongList = db.getSong();
        Log.d("Showlist", "Updated song list size: " + updatedSongList.size());
        songal.clear();
        songal.addAll(updatedSongList);
        customAdapter.notifyDataSetChanged();
        db.close();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            DBHelper db = new DBHelper(Showlist.this);
            songal.clear();
            songal.addAll(db.getSong());
            customAdapter.notifyDataSetChanged();
            db.close();
        }
    }
    }
