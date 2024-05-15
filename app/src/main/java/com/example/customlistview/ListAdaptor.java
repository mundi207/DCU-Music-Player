package com.example.customlistview;

import android.content.ClipData;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class ListAdaptor extends BaseAdapter {
    private Context ConText;
    private ArrayList<Itemdata> ItemData = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private TextView textView;
    private Button stopbtn;
    private LinearLayout linearLayout;


    public ListAdaptor(Context in_context, ArrayList<Itemdata> in_itemdata) {
        this.ConText = in_context;
        this.ItemData = in_itemdata;
    }
    @Override
    public int getCount() { // 화면에 표시하는 데이터의 갯수 반환
        return ItemData.size();
    }

    @Override
    public Object getItem(int position) { // 인자로 받은 위치의 데이터를 반환
        return ItemData.get(position);
    }

    @Override
    public long getItemId(int position) { // 인자로 받은 위치의 데이터의 id구분자를 반환
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) { // 인자로 받은 위치의 데이터가 화면에 표시될 뷰를 반환
        LayoutInflater inflater = (LayoutInflater) ConText.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_layout, parent, false);

        textView = (TextView) view.findViewById(R.id.text);
        stopbtn = (Button) view.findViewById(R.id.stopbtn);
        linearLayout = (LinearLayout) view.findViewById(R.id.layout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(mediaPlayer == null) {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource("/sdcard" + "/" + ItemData.get(position).getName()); // 파일 /sdcard
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    }
                } catch (IOException e) {
                    Toast.makeText(ConText.getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                }
            }
        });
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayer = null;
                }
            }
        });
        textView.setText(ItemData.get(position).getName());
        return view;
    }
}
