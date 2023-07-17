package sg.edu.rp.c346.id22024709.songsaver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    TextView teTitle2,teSinger2, teYear2;
    RadioGroup rgStars2;
    Button update, delete, cancel;

    RadioButton rg1, rg2, rg3, rg4, rg5;
    Song data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        teTitle2 = findViewById(R.id.teTitle2);
        teSinger2 = findViewById(R.id.teSinger2);
        teYear2 = findViewById(R.id.teYear2);
        rgStars2 = findViewById(R.id.rgStars);
        update =  findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);
        rg1 = findViewById(R.id.r1);
        rg2 = findViewById(R.id.r2);
        rg3 = findViewById(R.id.r3);
        rg4 = findViewById(R.id.r4);
        rg5 = findViewById(R.id.r5);

        Intent intentrec2 = getIntent();
        int pos = intentrec2.getIntExtra("position",0);
        data = (Song) intentrec2.getSerializableExtra("alPass");

        teTitle2.setText(data.getTitle());
        teSinger2.setText(data.getSingers());
        teYear2.setText(String.valueOf(data.getYear()));
        int rate = data.getStar();

        if (rate==1){
            rg1.setChecked(true);
        } else if (rate==2){
            rg2.setChecked(true);
        }else if (rate==3){
            rg3.setChecked(true);
        }else if (rate==4){
            rg4.setChecked(true);
        }else if (rate==5){
            rg5.setChecked(true);
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditActivity.this);
                data.setTitle(teTitle2.getText().toString());
                data.setSingers(teSinger2.getText().toString());
                data.setYear(Integer.parseInt(teYear2.getText().toString()));
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
                data.setStars(starz);


                int updateResult = db.updateSong(data);
                Log.d("EditActivity", "Update result: " + updateResult);

                db.close();
                setResult(Activity.RESULT_OK);
                finish();


            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(EditActivity.this);
                db.deleteNote(data.get_id());
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
