package com.mtha.mynoteproject;

import java.io.Serializable;

public class Note implements Serializable {
    int id;
    String tieuDe;
    String noiDung;
    String ngayTao;

    public Note() {
    }

    public Note(String tieuDe, String noiDung, String ngayTao) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
    }

    public Note(int id, String tieuDe, String noiDung, String ngayTao) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
    }
}
