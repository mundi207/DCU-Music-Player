package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> mp3List;
    File[] mp3File;
    String mp3Path = "/sdcard";
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        mp3File = new File(mp3Path).listFiles();

        ArrayList<Itemdata> itemlist = new ArrayList<Itemdata>();
        //권한 획득
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        for(File file : mp3File) { // 파일 횟수 만큼 반복
            String fileName = file.getName();
            String extName = fileName.substring(fileName.length() - 3);
            if(extName.equals("mp3") && file.isDirectory() == false) { // 폴더가 아니고 mp3 확장자 파일만 골라냄
                itemlist.add(new Itemdata(file.getName()));
            }
        }

        ListAdaptor adaptor = new ListAdaptor(getApplicationContext(), itemlist);
        listView.setAdapter(adaptor);
    }
}