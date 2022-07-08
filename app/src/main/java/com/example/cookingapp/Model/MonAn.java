package com.example.cookingapp.Model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.cookingapp.DAO.DB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MonAn implements Serializable {
    private  String tenmonan;
    private  String hinhanh;
    private  String tendaubep;
    private  String nguyenlieu;
    private List<ChiTiet> chitietcacbuoc;

    public MonAn(String tenmonan, String hinhanh, String tendaubep, String nguyenlieu, List<ChiTiet> chitietcacbuoc) {
        this.tenmonan = tenmonan;
        this.hinhanh = hinhanh;
        this.tendaubep = tendaubep;
        this.nguyenlieu = nguyenlieu;
        this.chitietcacbuoc = chitietcacbuoc;
    }
    public String getTenmonan() {
        return tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        this.tenmonan = tenmonan;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTendaubep() {
        return tendaubep;
    }

    public void setTendaubep(String tendaubep) {
        this.tendaubep = tendaubep;
    }

    public String getNguyenlieu() {
        return nguyenlieu;
    }

    public void setNguyenlieu(String nguyenlieu) {
        this.nguyenlieu = nguyenlieu;
    }

    public List<ChiTiet> getChitietcacbuoc() {
        return chitietcacbuoc;
    }

    public void setChitietcacbuoc(List<ChiTiet> chitietcacbuoc) {
        this.chitietcacbuoc = chitietcacbuoc;
    }


    public MonAn()
    {
    }

}
