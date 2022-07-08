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
            return ;
        }
        String query = (String) bundle.get("query");
        loadMonAn(query);
    }
    public void loadMonAn(String query)
    {
        List<String> haisan = new ArrayList<>();
        haisan.add("tôm");
        haisan.add("sò");
        haisan.add("mực");
        haisan.add("cá");
        haisan.add("cua");
        List<String> raucu = new ArrayList<>();
        raucu.add("rau");
        raucu.add("khoai");
        raucu.add("cà rốt");
        raucu.add("hành");
        raucu.add("cải");
        raucu.add("cà chua");
        raucu.add("bí");

        listMonAn = new ArrayList<>();
        chiTietList = new ArrayList<>();
        monanAdapter = new MonAnAdapter(listMonAn, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvDSMA.setLayoutManager(linearLayoutManager);
        rcvDSMA.setAdapter(monanAdapter);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("monan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren())
                {
                    String tenmonan  = snap.child("tenmonan").getValue(String.class);
                    String tennguyenlieu = snap.child("nguyenlieu").getValue(String.class);
                    final String querryCopy = query;
                    if ("hải sản" == query) {
                        for (int i = 0; i < haisan.size(); i++) {
                            if (tenmonan.toLowerCase(Locale.ROOT).contains(haisan.get(i)) ||
                                    tennguyenlieu.toLowerCase(Locale.ROOT).contains(haisan.get(i))) {
                                MonAn monAn = snap.getValue(MonAn.class);
                                listMonAn.add(monAn);
                                break;
                            }
                        }
                    }
                    //hải sản
                    else if (querryCopy.toLowerCase(Locale.ROOT) == "rau củ" || querryCopy.toLowerCase(Locale.ROOT) == "thực vật") {
                        for (int i = 0; i < haisan.size(); i++) {

                            if (tenmonan.toLowerCase(Locale.ROOT).contains(raucu.get(i)) ||
                                    tennguyenlieu.toLowerCase(Locale.ROOT).contains(raucu.get(i))) {
                                MonAn monAn = snap.getValue(MonAn.class);
                                listMonAn.add(monAn);
                                break;
                            }
                        }
                    }

                    if (tenmonan.toLowerCase(Locale.ROOT).contains(query) ||
                            tennguyenlieu.toLowerCase(Locale.ROOT).contains(query)) {
                        MonAn monAn = snap.getValue(MonAn.class);
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
