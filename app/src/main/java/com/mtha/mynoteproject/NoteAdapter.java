package com.mtha.mynoteproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class NoteAdapter extends ArrayAdapter<Note> {
    ArrayList<Note> listNote;
    public NoteAdapter(@NonNull Context context,  @NonNull ArrayList<Note> objects) {
        super(context, 0, objects);
        this.listNote = objects;
    }
    ActivityResultLauncher activityResultLauncher = ((AppCompatActivity)getContext()).registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                  if(result.getResultCode()==  ((AppCompatActivity)getContext()).RESULT_OK){

                      updNote(result.getData());

                  }
                }
            }
    );
    private void updNote(Intent intent) {
        Note note = (Note) intent.getExtras().get("note");
        int index = -1;
        for (Note n : listNote) {
            if (n.tieuDe.equals(note.tieuDe)) {
                index = listNote.indexOf(n);
                break;
            }
        }
        //cap nhat lai du lieu moi
        if (index != -1) {
            listNote.set(index, note);
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View curView = convertView;
        if(curView==null){
            curView = LayoutInflater.from(getContext()).inflate(R.layout.item_note,parent,false);
        }
        //lay ra doi tuong Note tai vi tri position
        Note note = getItem(position);
        //lay ra cac doi tuong view control tren item_view
        TextView tvTieuDe = curView.findViewById(R.id.tvTieuDe);
        TextView tvNgayTao = curView.findViewById(R.id.tvNgayTao);
        ImageButton imgEdit = curView.findViewById(R.id.Edit);
        ImageButton imgDel = curView.findViewById(R.id.Delete);
        //binding data
        tvTieuDe.setText(note.tieuDe);
        tvNgayTao.setText(note.ngayTao);
        //xu ly su kien tren cac imgButton
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent updIntent = new Intent(getContext(),AddNoteActivity.class);
               updIntent.putExtra("action","update");
               updIntent.putExtra("note", note);
               activityResultLauncher.launch(updIntent);

            }
        });

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xoa ghi chu").
                        setMessage("Ban co chac chan xoa ghi chu ngay "+note.ngayTao)
                        .setPositiveButton("Dong y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //xu ly xoa
                              if  (delNote(note)){
                                  Toast.makeText(getContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                              }else{
                                  Toast.makeText(getContext(), "khong xoa duoc", Toast.LENGTH_SHORT).show();
                              }
                            }
                        }).setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //xu ly huy
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        return curView;
    }
    private boolean delNote(Note note){
        boolean isDel = listNote.remove(note);
        notifyDataSetChanged();
        return isDel;
    }
}
