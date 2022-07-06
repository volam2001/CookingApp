package com.example.cookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.cookingapp.Adapter.MonAnAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cookingapp.Model.ChiTiet;
import com.example.cookingapp.Model.MonAn;
import com.example.cookingapp.R;
import com.example.cookingapp.ui.chitietmonan.ChiTietActivity;
import com.example.cookingapp.ui.listmonan.ListDSMNActivity;
import com.example.cookingapp.ui.themmonan.ThemMonActivity;
import com.example.cookingapp.ui.timkiem.TimKiemActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity {
    private RecyclerView rcvDSMA, rcvDSMADN, rcvbanner;
    private MonAnAdapter monanAdapter, monandanauAdapter;
    private List<MonAn> listMonAn, listMonAnDaNau;
    private List<ChiTiet> chiTietList, chiTietdanauList;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhome);
        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home)
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                else if (id ==R.id.navlist)
                {
                    Intent intent = new Intent(getApplicationContext(), ListDSMNActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                else if (id ==R.id.navrec)
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                return true;
            }
        });
        searchView = findViewById(R.id.editTimkiem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(getApplicationContext(), TimKiemActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                TimKiemActivity timKiemActivity = new TimKiemActivity();
                Bundle bundle = new Bundle();
                bundle.putString("query", s);
                intent.putExtras(bundle);
                getApplicationContext().startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("TAG", s);
                return false;
            }
        });

        floatingActionButton = findViewById(R.id.btnthemmonan);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThemMonActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        loadMonAn();
        loadMonAnDaNau();
    }

    @Override
    public void onBackPressed() {

    }

    public void loadMonAn()
    {
        rcvDSMA = findViewById(R.id.recycleDSMN);
        listMonAn = new ArrayList<>();
        chiTietList = new ArrayList<>();
        monanAdapter = new MonAnAdapter(listMonAn, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvDSMA.setLayoutManager(linearLayoutManager);
        rcvDSMA.setAdapter(monanAdapter);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("monan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMonAn.clear();

                for(DataSnapshot snap: snapshot.getChildren())
                {
                    chiTietList = new ArrayList<>();

                    String tendaubep = snap.child("tendaubep").getValue(String.class);
                    String hinhanh = snap.child("hinhanh").getValue(String.class);
                    String tenmonan  = snap.child("tenmonan").getValue(String.class);
                    DataSnapshot dataref = snap.child("chitietmonan");
//                    chiTietList.clear();
                    for (DataSnapshot item: dataref.getChildren())
                    {
                        String link = item.child("link").getValue(String.class);
                        String chitiet = item.child("chitiet").getValue(String.class);
                        chiTietList.add(new ChiTiet(link, chitiet));
                    }
                    String nguyenlieu =  snap.child("nguyenlieu").getValue(String.class);

                    MonAn monAn = new MonAn(tenmonan, hinhanh, tendaubep, nguyenlieu, chiTietList);

                    listMonAn.add(monAn);
                    Log.d("===================*************===============", "onDataChange: ");

                }
                monanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void loadMonAnDaNau()
    {
        rcvDSMADN = findViewById(R.id.recycleDSMADN);
        listMonAnDaNau = new ArrayList<>();
        chiTietdanauList = new ArrayList<>();
        monandanauAdapter = new MonAnAdapter(listMonAnDaNau, getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvDSMADN.setLayoutManager(linearLayoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        rcvDSMADN.setAdapter(monandanauAdapter);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("monan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMonAnDaNau.clear();

                for(DataSnapshot snap: snapshot.getChildren())
                {

                    String tendaubep = snap.child("tendaubep").getValue(String.class);
                    Long danau  = snap.child("danau").getValue(Long.class);
                    String hinhanh = snap.child("hinhanh").getValue(String.class);
                    String tenmonan  = snap.child("tenmonan").getValue(String.class);
                    DataSnapshot dataref = snap.child("chitietmonan");
                    chiTietdanauList = new ArrayList<>();

                    for (DataSnapshot item: dataref.getChildren())
                    {
                        if (item.child("link") != null) {
                            String link = item.child("link").getValue(String.class);
                            String chitiet = item.child("chitiet").getValue(String.class);
                            chiTietdanauList.add(new ChiTiet(link, chitiet));
                        }
                    }
                    String nguyenlieu =  snap.child("nguyenlieu").getValue(String.class);

                    MonAn monAn = new MonAn(tenmonan, hinhanh, tendaubep, nguyenlieu, chiTietdanauList);
                    if ((Long)danau == 1)
                    {
                        listMonAnDaNau.add(monAn);
                    }

                }
                monandanauAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

    });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
//        arrayList_MA  = monAnController.getDataListMN();
//        DanhSachMonAnAdapter danhSachBaiHatAdapter = new DanhSachMonAnAdapter(arrayList_MA,this);
//        rcvDSMA.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        rcvDSMA.setAdapter(danhSachBaiHatAdapter);
//
//        arrayList_BHM  = baiHatController.getDataBaihatmoi();
//        DanhSachBaiHatMoiAdapter danhSachBaiHatMoiAdapter = new DanhSachBaiHatMoiAdapter(arrayList_BHM,this);
//        rcvDSBHM.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        rcvDSBHM.setAdapter(danhSachBaiHatMoiAdapter);
    }
}
