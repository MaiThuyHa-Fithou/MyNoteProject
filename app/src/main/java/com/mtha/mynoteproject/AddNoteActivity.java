package com.mtha.mynoteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {
    EditText etTieuDe, etNoiDung, etNgayTao;

    String msg ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        getViews();
        //khai bao 1 doi tuong intent de chua doi tuong intent duoc truyen sang
        //thong qua phuong thuc getIntent()
        Intent intentReceipt = getIntent();
        msg = intentReceipt.getStringExtra("action");
        if(msg.equals("update")){
            //set du lieu len cac doi tuong view
            Note note =(Note) intentReceipt.getSerializableExtra("note");
            etTieuDe.setText(note.tieuDe);
            etNoiDung.setText(note.noiDung);
            etNgayTao.setText(note.ngayTao);
        }
    }

    public void onSaveNote(View view) {
        if(msg.equals("insert")){
            //put du lieu ve phia MainActivity
            Intent intentResult= new Intent();
            intentResult.putExtra("action", "insert");
            //lay ra doi tuong ghi chu va truyen ve MainActivity
            Note note = getNote();
            Bundle bundle = new Bundle();
            bundle.putSerializable("note", note);
            //day doi tuong  bundle vao intent
            intentResult.putExtras(bundle);
            setResult(RESULT_OK,intentResult);
            finish();
        }else if(msg.equals("update")){
            //day du lieu ve MainActivity
            Intent intent = new Intent();
            intent.putExtra("action","update");
            Note note = getNote();
            Bundle bundle = new Bundle();
            bundle.putSerializable("note", note);
            //day doi tuong  bundle vao intent
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
    private void getViews(){
        etTieuDe = findViewById(R.id.etTieuDe);
        etNoiDung = findViewById(R.id.etNoiDung);
        etNgayTao = findViewById(R.id.etNgayTao);
    }
    //phuong thuc lay ra doi tuong ghi chu - note
    private Note getNote(){
        String tieuDe = etTieuDe.getText().toString();
        String noiDung = etNoiDung.getText().toString();
        String ngayTao = etNgayTao.getText().toString();
        return new Note(tieuDe,noiDung,ngayTao);
    }

    public void onSelectDate(View view) {
        //lay ra thong tin ve ngay tao
        Calendar c = Calendar.getInstance();//lay ra ngay hien tai
        int ngay = c.get(Calendar.DAY_OF_MONTH);
        int thang = c.get(Calendar.MONTH);
        int nam = c.get(Calendar.YEAR);
        //tao 1 doi tuong DatePickerDialog de hien thi dialog chon ngay thang nam
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddNoteActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                //set gia tri ngay thang nam cho doi tuong etNgayTao
                etNgayTao.setText(date+"/" +(month+1) +"/" + year);
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}