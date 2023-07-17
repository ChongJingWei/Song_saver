package sg.edu.rp.c346.id22024709.songsaver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;


    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitleSong = rowView.findViewById(R.id.tvTitleSong);
        TextView tvYearSong = rowView.findViewById(R.id.tvYearSong);
        TextView tvRatingSong = rowView.findViewById(R.id.tvRatingSong);
        TextView tvSingerSong = rowView.findViewById(R.id.tvSingerSong);

        Song currentSong = songList.get(position);

        tvTitleSong.setText(currentSong.getTitle());
        tvYearSong.setText(String.valueOf(currentSong.getYear()));
        tvRatingSong.setText(("★".repeat(currentSong.getStar())));
        tvSingerSong.setText(currentSong.getSingers());

        return rowView;
    }

}
