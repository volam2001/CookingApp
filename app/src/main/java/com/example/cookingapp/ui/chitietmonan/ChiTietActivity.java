package com.example.cookingapp.ui.chitietmonan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.Adapter.ChiTietAdapter;
import com.example.cookingapp.Adapter.MonAnAdapter;
import com.example.cookingapp.MainActivity;
import com.example.cookingapp.Model.ChiTiet;
import com.example.cookingapp.Model.MonAn;
import com.example.cookingapp.R;

import java.util.ArrayList;
import java.util.List;

public class ChiTietActivity extends AppCompatActivity {
    private List<ChiTiet> chiTietList;
    private RecyclerView rcvdongchitiet;
    private ChiTietAdapter chiTietAdapter;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietmonan);
        btnHome = findViewById(R.id.btnHome);
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
//        MonAn monAn = (MonAn) bundle.get("monan");
//        Log.d(monAn.getTenmonan(), "onCreate fmiajfoiqfowqifjqwoijf: ");
        rcvdongchitiet = findViewById(R.id.rcvdongchitiet);
        rcvdongchitiet.findViewById(R.id.tvchitiet);
//        chiTietList = monAn.getChitietcacbuoc();
        List<ChiTiet> chiTietList = (List<ChiTiet>) bundle.get("chiTietList");

        chiTietAdapter = new ChiTietAdapter(chiTietList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvdongchitiet.setLayoutManager(linearLayoutManager);
        rcvdongchitiet.setAdapter(chiTietAdapter);
    }
}
