package com.example.cookingapp.ui.chitietmonan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingapp.Adapter.ChiTietAdapter;
import com.example.cookingapp.Adapter.MonAnAdapter;
import com.example.cookingapp.MainActivity;
import com.example.cookingapp.Model.ChiTiet;
import com.example.cookingapp.Model.MonAn;
import com.example.cookingapp.R;
import com.example.cookingapp.ui.danau.DaNauActivity;
import com.example.cookingapp.ui.yeuthich.YeuThichActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChiTietActivity extends AppCompatActivity {
    private List<ChiTiet> chiTietList;
    private RecyclerView rcvdongchitiet;
    private ChiTietAdapter chiTietAdapter;
    private Button btnHome, btndone, btnlove;
    private TextView txtintroduce;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietmonan);
        btnHome = findViewById(R.id.btnHome);
        btnlove = findViewById(R.id.btnyeuthich);
        btndone = findViewById(R.id.btndone);

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
                else if (id ==R.id.navdanau)
                {
                    Intent intent = new Intent(getApplicationContext(), DaNauActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                else if (id ==R.id.navyeuthich)
                {
                    Intent intent = new Intent(getApplicationContext(), YeuThichActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                return true;
            }
        });
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
        MonAn monAn = (MonAn) bundle.get("monAn");
        rcvdongchitiet = findViewById(R.id.rcvdongchitiet);
        rcvdongchitiet.findViewById(R.id.tvchitiet);
        txtintroduce = findViewById(R.id.txtintroduce);
        String introduce = monAn.getTenmonan();
        txtintroduce.setText(introduce);
        chiTietList = monAn.getChitietcacbuoc();
        chiTietAdapter = new ChiTietAdapter(chiTietList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvdongchitiet.setLayoutManager(linearLayoutManager);
        rcvdongchitiet.setAdapter(chiTietAdapter);
        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                databaseReference.child("danau/"+monAn.getTenmonan()).setValue(monAn);
                Toast toast = Toast.makeText(getApplicationContext(), "Bạn thật giỏi quá", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btnlove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();
                databaseReference.child("yeuthich/"+monAn.getTenmonan()).setValue(monAn);
                Toast toast = Toast.makeText(getApplicationContext(), "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
