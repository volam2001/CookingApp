package com.example.cookingapp.ui.timkiem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.Adapter.MonAnAdapter;
import com.example.cookingapp.MainActivity;
import com.example.cookingapp.Model.ChiTiet;
import com.example.cookingapp.Model.MonAn;
import com.example.cookingapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TimKiemActivity extends AppCompatActivity {
    private List<MonAn> listMonAn;
    private RecyclerView rcvDSMA;
    private List <ChiTiet> chiTietList;
    private MonAnAdapter monanAdapter;
    private Button btnHome;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ketquatimkiem);
        btnHome = findViewById(R.id.btnHome);
        rcvDSMA  =findViewById(R.id.rcvdongketqua);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
        Bundle bundle =  getIntent().getExtras();
        if (bundle == null)
        {
            Log.d("Empty", "onCreate: ");
            return ;
        }
        String query = (String) bundle.get("query");
        Log.d("Câu query là:", query);
        loadMonAn(query);
    }
    public void loadMonAn(String query)
    {
        listMonAn = new ArrayList<>();
        chiTietList = new ArrayList<>();
        monanAdapter = new MonAnAdapter(listMonAn, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvDSMA.setLayoutManager(linearLayoutManager);
        rcvDSMA.setAdapter(monanAdapter);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        Log.d(query, "Kết quả tìm kiếm: ");
        databaseReference.child("monan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("load", "onDataChange: ");

                for(DataSnapshot snap: snapshot.getChildren())
                {
                    chiTietList = new ArrayList<>();

                    String tenmonan  = snap.child("tenmonan").getValue(String.class);
                    String tennguyenlieu = snap.child("nguyenlieu").getValue(String.class);
                    if (tenmonan.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)) ||
                        tennguyenlieu.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                        DataSnapshot dataref = snap.child("chitietmonan");
                        for (DataSnapshot item : dataref.getChildren()) {
                            String link = item.child("link").getValue(String.class);
                            String chitiet = item.child("chitiet").getValue(String.class);
                            chiTietList.add(new ChiTiet(link, chitiet));
                        }
                        //ớt
//
                        MonAn monAn = snap.getValue(MonAn.class);
//                        monAn.setChitietcacbuoc(chiTietList);
                        listMonAn.add(monAn);
                    }

                }
                monanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
