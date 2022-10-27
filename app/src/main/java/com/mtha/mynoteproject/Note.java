package com.mtha.mynoteproject;

import java.io.Serializable;

public class Note implements Serializable {
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
}
