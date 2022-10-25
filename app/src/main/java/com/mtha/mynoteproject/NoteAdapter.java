package com.mtha.mynoteproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(@NonNull Context context,  @NonNull ArrayList<Note> objects) {
        super(context, 0, objects);
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
                Toast.makeText(getContext(), "Day la nut sua", Toast.LENGTH_LONG).show();
            }
        });

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Day la nut xoa", Toast.LENGTH_LONG).show();
            }
        });
        return curView;
    }
}
