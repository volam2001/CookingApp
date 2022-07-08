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
import com.example.cookingapp.ui.danau.DaNauActivity;
import com.example.cookingapp.ui.listmonan.ListDSMNActivity;
import com.example.cookingapp.ui.themmonan.ThemMonActivity;
import com.example.cookingapp.ui.timkiem.TimKiemActivity;
import com.example.cookingapp.ui.yeuthich.YeuThichActivity;
import com.example.cookingapp.ui.yeuthich.YeuThichActivity;
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
import java.util.Locale;

public class MainActivity  extends AppCompatActivity {
    private RecyclerView rcvDSMA, rcvDSMADN;
    private MonAnAdapter monanAdapter, monandexuatAdapter;
    private List<MonAn> listMonAn, listMonAnDaNau, listMonAnDeXuat;
    private FloatingActionButton floatingActionButton;
    private SearchView searchView;
    private NavigationView navigationView;
    private List<String> monAnChuaNau;

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
        checkMonDaNau();
    }

    @Override
    public void onBackPressed() {

    }

    public void loadMonAn()
    {
        rcvDSMA = findViewById(R.id.recycleDSMN);
        listMonAn = new ArrayList<>();
        monanAdapter = new MonAnAdapter(listMonAn, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvDSMA.setLayoutManager(linearLayoutManager);
        rcvDSMA.setAdapter(monanAdapter);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("monan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren())
                {
                    MonAn monAn = (MonAn) snap.getValue(MonAn.class);
                    listMonAn.add(monAn);
                }
                monanAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void checkMonDaNau()
    {
        List<String> answer = new ArrayList<>();
        listMonAnDaNau = new ArrayList<>();
        List<String> monan_stringFlag = new ArrayList<>();
        monan_stringFlag.add("vịt");
        monan_stringFlag.add("canh");
        monan_stringFlag.add("rau");
        monan_stringFlag.add("cá");
        monan_stringFlag.add("kho");
        monan_stringFlag.add("thịt");
        monan_stringFlag.add("gà");
        monan_stringFlag.add("bò");

        List<Boolean> monan_booleanFlag = new ArrayList<>();
        monan_booleanFlag.add(true);
        monan_booleanFlag.add(true);
        monan_booleanFlag.add(true);
        monan_booleanFlag.add(true);
        monan_booleanFlag.add(true);
        monan_booleanFlag.add(true);
        monan_booleanFlag.add(true);
        monan_booleanFlag.add(true);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.child("monan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren())
                {
                    MonAn monAn = (MonAn) snap.getValue( MonAn.class);
                    if (monAn.getDanau() ==Long.valueOf(1)) {
                        for (int i = 0; i < monan_booleanFlag.size(); i++) {
                            if (monAn.getTenmonan().toLowerCase(Locale.ROOT) == monan_stringFlag.get(i)) {

                                monan_booleanFlag.set(i, false);
                            }
                        }
                    }
                }
                for (int i = 0; i< monan_booleanFlag.size(); i++)
                {
                    Log.d(String.valueOf(monan_booleanFlag.get(i)) + String.valueOf(i), "gias trij cua cac co: ");
                    if (monan_booleanFlag.get(i) == true)
                    {
                        answer.add(monan_stringFlag.get(i));
                    }

                }
                loadMonAnDeXuat(answer);

                //
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
    public void loadMonAnDeXuat(List<String> ans)
    {
        for(int i= 0; i< ans.size(); i++)
        {
            Log.d(ans.get(i), "loadMonAnDeXuat: ");
        }
        rcvDSMADN = findViewById(R.id.recycleDSMADN);
        listMonAnDeXuat = new ArrayList<>();
        monandexuatAdapter = new MonAnAdapter(listMonAnDeXuat, getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvDSMADN.setLayoutManager(linearLayoutManager);
        rcvDSMADN.setAdapter(monandexuatAdapter);
        List<String> monAnChuaNau = new ArrayList<>();
        ////
        //code sẽ chèn ở đây
        // hàm check món đã nấu -> para
        //hàm đề xuất (hàm check món đã nấu)
        ////
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        List<String> finalMonAnChuaNau = ans;
        databaseReference.child("monan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap: snapshot.getChildren())
                {
                    MonAn monAn = (MonAn) snap.getValue( MonAn.class);
                    String tenMon = monAn.getTenmonan();
                    if (finalMonAnChuaNau != null) {
                        for (int i = 0; i < finalMonAnChuaNau.size(); i++) {
                            if (monAn.getTenmonan().toLowerCase(Locale.ROOT).contains(finalMonAnChuaNau.get(i).toLowerCase(Locale.ROOT))) {
                                listMonAnDeXuat.add(monAn);
                                break;
                            }
                        }
                    }
                }
                monandexuatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

    });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
