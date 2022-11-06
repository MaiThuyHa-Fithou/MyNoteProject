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
    NoteProvider noteProvider = new NoteProvider(MainActivity.this);//dung de thao tac voi datbase - tblNote
    //khai bao mot doi tuong xu ly kq tra ve ActivityResultLaucher


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //lay du lieu tu database
        data = noteProvider.getAllNote();
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
                  //insert du lieu vao database
                   if(noteProvider.insNote(note)){
                       Toast.makeText(MainActivity.this, "Insert success!",
                               Toast.LENGTH_SHORT).show();
                       //refesh lai du lieu
                       data.clear();
                       data.addAll(noteProvider.getAllNote());
                       adapter.notifyDataSetChanged();
                       lvNotes.invalidateViews();
                       lvNotes.refreshDrawableState();
                   }else {
                       Toast.makeText(MainActivity.this, "Insert fail!",
                               Toast.LENGTH_SHORT).show();
                   }

               }
            }

        }
    });

}