package com.mtha.mynoteproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvNotes;
    ArrayList<Note> data = new ArrayList<>();
    NoteAdapter adapter;
    FloatingActionButton addNote;
    final static int INS=1001;//requestcode de them moi
    //khai bao mot doi tuong xu ly kq tra ve ActivityResultLaucher


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initData();
        //khoi tao adapter
        adapter = new NoteAdapter(MainActivity.this,data);
        //tim listview va set adapter cho listview
        lvNotes = findViewById(R.id.lvNote);
        lvNotes.setAdapter(adapter);
        addNote = findViewById(R.id.AddNote);
        //xu ly su kien onClick
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //khai bao va khoi tao 1 doi tuong intent
                Intent insIntent = new Intent(MainActivity.this,AddNoteActivity.class);
                //put du lieu vao doi tuong intent
                insIntent.putExtra("action","insert");
                activityResultLauncher.launch(insIntent);
            }
        });


    }
    private void updNote(Intent intent){
        Note note =(Note)intent.getSerializableExtra("note");
        int index =-1;
        for (Note n: data){
            if(n.tieuDe.equals(note.tieuDe)){
                index = data.indexOf(n);
                break;
            }
        }
        //cap nhat lai du lieu moi
        if(index!=-1) {
            data.set(index, note);
            adapter.notifyDataSetChanged();
        }
    }
    //khoi tao va dang ky xu ly
    ActivityResultLauncher activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            //lay du lieu tra ve
            if(result.getResultCode()==RESULT_OK){
                //lay du lieu duoc truyen ve
               String action = result.getData().getStringExtra("action");
               if(action.equals("insert")){
                   //them du lieu vao trong list data source
                   Note note =(Note) result.getData().getExtras().get("note");
                   data.add(note);
                   adapter.notifyDataSetChanged();
               }
            }

        }
    });
    private void initData(){
        data.add(new Note("Di choi","Xem phim sau do la di an","23/10/2022"));
        data.add(new Note("Di hoc","hoc gi kho the?","24/10/2022"));
        data.add(new Note("Buon qua","Buon thi di choi thoi","25/10/2022"));
        data.add(new Note("Co hen voi nguoi ieu","Xem phim sau do la di an","26/10/2022"));
    }
}