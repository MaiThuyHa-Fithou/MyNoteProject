package com.mtha.mynoteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Lop trung gian de thao tac giua table voi model
 */
public class NoteProvider {
    Context context;
    DbOpenHelper openHelper;
    SQLiteDatabase db; //doi tuong database de thao tac voi csdl
    //dinh nghia cac ten cot trong bang tblNote
    final static String TB_NAME ="tblNote";
    final static String _ID = "id";
    final static String TIEU_DE="tieuDe";
    final static String NOI_DUNG="noiDung";
    final static String NGAY_TAO ="ngayTao";

    public NoteProvider(Context context){
        this.context = context;
        openHelper = new DbOpenHelper(context); //khoi tao doi tuong openHelper de thuc thi csdl
    }
    //cac phuong thuc thao tac voi csdl tblNote
    public boolean insNote(Note note){
        //lay ra csdl dang doc ghi
        db = openHelper.getWritableDatabase();
        //tao doi tuong contentvalue de chua cac gia tri can insert
        ContentValues values = new ContentValues();
        values.put(TIEU_DE, note.tieuDe);
        values.put(NOI_DUNG, note.noiDung);
        values.put(NGAY_TAO, note.ngayTao);
        //thuc hien cau lenh insert
        if(db.insert(TB_NAME,null,values)==-1)
            return false;
        return true;
    }
    public int updNote(Note note, String tieuDe){
        //lay ra doi tuong database de thuc hien doc ghi
        db = openHelper.getWritableDatabase();
        //doi tuong contentvalue chua cac gia tri can cap nhat
        ContentValues values = new ContentValues();
        values.put(NOI_DUNG, note.noiDung);
        values.put(NGAY_TAO, note.ngayTao);
        //khai bao mang dieu kien cap nhat
        String [] dk={tieuDe};
        //thuc hien cau lenh update
        return db.update(TB_NAME,values,TIEU_DE +"=?",dk);
    }

    public int delNote(String id){
        //lay ra doi tuong database de thuc hien xoa du lieu
        db = openHelper.getWritableDatabase();
        return  db.delete(TB_NAME,_ID+"=?",new String[]{id});
    }
    public ArrayList<Note> getAllNote(){
        ArrayList<Note> kq =new ArrayList<>();
        //lay database de thuc hien doc du lieu
        db = openHelper.getReadableDatabase();
        String sql ="SELECT * FROM " + TB_NAME;
        Cursor cs = db.rawQuery(sql,null);
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String tieude = cs.getString(1);
            String noidung = cs.getString(2);
            String ngay = cs.getString(3);
            Note note = new Note(id,tieude,noidung,ngay);
            kq.add(note);
        }
        return  kq;
    }
}
