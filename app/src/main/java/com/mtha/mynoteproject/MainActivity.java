package com.mtha.mynoteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvNotes;
    ArrayList<Note> data = new ArrayList<>();
    NoteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        //khoi tao adapter
        adapter = new NoteAdapter(MainActivity.this,data);
        //tim listview va set adapter cho listview
        lvNotes = findViewById(R.id.lvNote);
        lvNotes.setAdapter(adapter);
    }

    private void initData(){
        data.add(new Note("Di choi","Xem phim sau do la di an","23/10/2022"));
        data.add(new Note("Di hoc","hoc gi kho the?","24/10/2022"));
        data.add(new Note("Buon qua","Buon thi di choi thoi","25/10/2022"));
        data.add(new Note("Co hen voi nguoi ieu","Xem phim sau do la di an","26/10/2022"));
    }
}