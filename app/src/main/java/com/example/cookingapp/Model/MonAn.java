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
    private  String idlist;
    private  String tenmonan;
    private  String hinhanh;
    private  String tendaubep;
    private  String nguyenlieu;
    private List<ChiTiet> chitietcacbuoc;
    private Long danau;



    public MonAn(String tenmonan, String hinhanh, String tendaubep, String nguyenlieu, List<ChiTiet> chitietcacbuoc) {
        this.tenmonan = tenmonan;
        this.hinhanh = hinhanh;
        this.tendaubep = tendaubep;
        this.nguyenlieu = nguyenlieu;
        this.chitietcacbuoc = chitietcacbuoc;
        this.danau = Long.valueOf(0);

    }
    public MonAn()
    {

    }
    public String getId_playlist() {
        return idlist;
    }
    public ArrayList<String> getDataListBanner(){
        ArrayList<String> arrayList = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("banner").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot snap: snapshot.getChildren())
                {
                    String link = snap.getValue(String.class);
                    Log.d(link,"Sucess");
                    arrayList.add(link);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return arrayList;
    }


    public String getId() {
        return idlist;
    }

    public void setId(String id) {
        this.idlist = id;
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

    public void setTendaubep(String tencasi) {
        this.tendaubep = tendaubep;
    }

    public String getNguyenlieu() {
        return nguyenlieu;
    }
    public void setNguyenlieu(String theloai) {
        this.nguyenlieu = theloai;
    }

    public List<ChiTiet> getChitietcacbuoc() {
        return chitietcacbuoc;
    }

    public void setChitietcacbuoc(List<ChiTiet> chitietcacbuoc) {
        this.chitietcacbuoc = chitietcacbuoc;
    }






}
